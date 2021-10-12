package Board_module;

/**
 * Copyright(c) 2001 iSavvix Corporation (http://www.isavvix.com/)
 *
 *                        All rights reserved
 *
 * Permission to use, copy, modify and distribute this material for
 * any purpose and without fee is hereby granted, provided that the
 * above copyright notice and this permission notice appear in all
 * copies, and that the name of iSavvix Corporation not be used in
 * advertising or publicity pertaining to this material without the
 * specific, prior written permission of an authorized representative of
 * iSavvix Corporation.
 *
 * ISAVVIX CORPORATION MAKES NO REPRESENTATIONS AND EXTENDS NO WARRANTIES,
 * EXPRESS OR IMPLIED, WITH RESPECT TO THE SOFTWARE, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR ANY PARTICULAR PURPOSE, AND THE WARRANTY AGAINST
 * INFRINGEMENT OF PATENTS OR OTHER INTELLECTUAL PROPERTY RIGHTS.  THE
 * SOFTWARE IS PROVIDED "AS IS", AND IN NO EVENT SHALL ISAVVIX CORPORATION OR
 * ANY OF ITS AFFILIATES BE LIABLE FOR ANY DAMAGES, INCLUDING ANY
 * LOST PROFITS OR OTHER INCIDENTAL OR CONSEQUENTIAL DAMAGES RELATING
 * TO THE SOFTWARE.
 *
 */


import java.sql.*; //데이터 소스(Mysql)에 저장된 데이터 접근 및 처리를 위한 API 제공
import java.util.Properties;
//Properties(속성): 스트림에 저장하거나 스트림에서 로드할 수 있습니다. 속성 목록의 각 키와 해당 값은 문자열입니다.

import java.util.Vector;
//개체의 유동적인 배열을 구현가능함, Vector가 생성된 후 항목 추가 및 제거를 수용하기위해 필요에 따라 커지거나 줄어들 수 있습니다.


/**
 * Manages a java.sql.Connection pool.
 *
 * @author  Anil Hemrajani
 */
public class DBConnectionMgr {
    private Vector connections = new Vector(10);
    private String _driver = "com.mysql.cj.jdbc.Driver",//Mysql과 JSP를 연동시켜주는 JDBC드라이버 입니다. 
    //_url = "jdbc:mysql://127.0.0.1:3306/homepagedb?serverTimezone=UTC&characterEncording=UTF-8",//로컬
    _url = "jdbc:mysql://database-1.co9pinwozv4n.ap-northeast-2.rds.amazonaws.com:3306/homepagedb?serverTimezone=UTC&characterEncording=UTF-8",
    _user = "root",
    //_password = "1234";
    _password = "abcd1234";
    
    private boolean _traceOn = false;
    private boolean initialized = false;
    private int _openConnections = 50;
    private static DBConnectionMgr instance = null;

    public DBConnectionMgr() {
    }

    /** Use this method to set the maximum number of open connections before
     unused connections are closed.
     */ 
    //:이 매서드는 이전에 열려 있는 최대 connections의 수를 설정합니다. 사용되지 않은 Connections은 닫힙니다.

    public static DBConnectionMgr getInstance() {
        if (instance == null) {
            synchronized (DBConnectionMgr.class) {
                if (instance == null) {
                    instance = new DBConnectionMgr();
                }
            }
        }

        return instance;
    }
    
    public void setOpenConnectionCount(int count) {
        _openConnections = count;
    }


    public void setEnableTrace(boolean enable) {
        _traceOn = enable;
    }


    /** Returns a Vector of java.sql.Connection objects */
    // Returns a Vector of java.sql.Connection objects 의 벡터 크기를 반환합니다.
    public Vector getConnectionList() {
        return connections;
    }


    /** Opens specified "count" of connections and adds them to the existing pool */
    //지정된 '수'의 Connection에 열고 Pool에 추가합니다.
    public synchronized void setInitOpenConnections(int count)
            throws SQLException {
        Connection c = null;
        ConnectionObject co = null;

        for (int i = 0; i < count; i++) {
            c = createConnection();
            co = new ConnectionObject(c, false);

            connections.addElement(co);
            trace("ConnectionPoolManager: Adding new DB connection to pool (" + connections.size() + ")");
        }
    }


    /** Returns a count of open connections */
    //	열린 Connections 수를 반환합니다.
    public int getConnectionCount() {
        return connections.size();
    }


    /** Returns an unused existing or new connection.  */
    // 사용하지 않은 Connection 또는 새 연결을 반환합니다. 
    public synchronized Connection getConnection()
            throws Exception {
        if (!initialized) {
            Class c = Class.forName(_driver);
            DriverManager.registerDriver((Driver) c.newInstance());

            initialized = true;
        }


        Connection c = null;
        ConnectionObject co = null;
        boolean badConnection = false;


        for (int i = 0; i < connections.size(); i++) {
            co = (ConnectionObject) connections.elementAt(i);

            // If connection is not in use, test to ensure it's still valid!
            if (!co.inUse) {
                try {
                    badConnection = co.connection.isClosed();
                    if (!badConnection)
                        badConnection = (co.connection.getWarnings() != null);
                } catch (Exception e) {
                    badConnection = true;
                    e.printStackTrace();
                }

                // Connection is bad, remove from pool
                if (badConnection) {
                    connections.removeElementAt(i);
                    trace("ConnectionPoolManager: Remove disconnected DB connection #" + i);
                    continue;
                }

                c = co.connection;
                co.inUse = true;

                trace("ConnectionPoolManager: Using existing DB connection #" + (i + 1));
                break;
            }
        }

        if (c == null) {
            c = createConnection();
            co = new ConnectionObject(c, true);
            connections.addElement(co);

            trace("ConnectionPoolManager: Creating new DB connection #" + connections.size());
        }

        return c;
    }


    /** Marks a flag in the ConnectionObject to indicate this connection is no longer in use */
    //	이 연결이 더 이상 사용되지 않음을 나타내기 위해 ConnectionObject에 플래그를 표시합니다.
    public synchronized void freeConnection(Connection c) {
        if (c == null)
            return;

        ConnectionObject co = null;

        for (int i = 0; i < connections.size(); i++) {
            co = (ConnectionObject) connections.elementAt(i);
            if (c == co.connection) {
                co.inUse = false;
                break;
            }
        }

        for (int i = 0; i < connections.size(); i++) {
            co = (ConnectionObject) connections.elementAt(i);
            if ((i + 1) > _openConnections && !co.inUse)
                removeConnection(co.connection);
        }
    }

    public void freeConnection(Connection c, PreparedStatement p, ResultSet r) {
        try {
            if (r != null) r.close();
            if (p != null) p.close();
            freeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeConnection(Connection c, Statement s, ResultSet r) {
        try {
            if (r != null) r.close();
            if (s != null) s.close();
            freeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeConnection(Connection c, PreparedStatement p) {
        try {
            if (p != null) p.close();
            freeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void freeConnection(Connection c, Statement s) {
        try {
            if (s != null) s.close();
            freeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /** Marks a flag in the ConnectionObject to indicate this connection is no longer in use */
    //이 연결이 더 이상 사용되지 않음을 나타내기 위해 ConnectionObject에 플래그를 표시합니다.
    public synchronized void removeConnection(Connection c) {
        if (c == null)
            return;

        ConnectionObject co = null;
        for (int i = 0; i < connections.size(); i++) {
            co = (ConnectionObject) connections.elementAt(i);
            if (c == co.connection) {
                try {
                    c.close();
                    connections.removeElementAt(i);
                    trace("Removed " + c.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            }
        }
    }


    private Connection createConnection()
            throws SQLException {
        Connection con = null;

        try {
            if (_user == null)
                _user = "";
            if (_password == null)
                _password = "";

            Properties props = new Properties();
            props.put("user", _user);
            props.put("password", _password);

            con = DriverManager.getConnection(_url, props);
        } catch (Throwable t) {
            throw new SQLException(t.getMessage());
        }

        return con;
    }


    /** Closes all connections and clears out the connection pool */
    //모든 Connections을 닫고 pool을 초기화 합니다.
    public void releaseFreeConnections() {
        trace("ConnectionPoolManager.releaseFreeConnections()");

        Connection c = null;
        ConnectionObject co = null;

        for (int i = 0; i < connections.size(); i++) {
            co = (ConnectionObject) connections.elementAt(i);
            if (!co.inUse)
                removeConnection(co.connection);
        }
    }


    /** Closes all connections and clears out the connection pool */
    
    public void finalize() {
        trace("ConnectionPoolManager.finalize()");

        Connection c = null;
        ConnectionObject co = null;

        for (int i = 0; i < connections.size(); i++) {
            co = (ConnectionObject) connections.elementAt(i);
            try {
                co.connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            co = null;
        }

        connections.removeAllElements();
    }


    private void trace(String s) {
        if (_traceOn)
            System.err.println(s);
    }

}


class ConnectionObject {
    public java.sql.Connection connection = null;
    public boolean inUse = false;

    public ConnectionObject(Connection c, boolean useFlag) {
        connection = c;
        inUse = useFlag;
    }
}
