package Board_module;

public class BoardBean {//public 선언을 지정하게 되면 외부에서 이 변수,메서드,클래스를 자유롭게 사용가능
	private String id;
	private int num;
	private String sort;
	private int count;
	private String title;
	private String content;     
	private int pos;                   
	private String logdate;              
	private String  ip;   
	// private public과 반대로 외부로부터 모든 접근을 차단합니다.
	// BoardBean Class에서 변수들은 Private 선언됬지만 
	// 변수들의 각각 get/set매서드들은 Public 선언되서 다른 Class에서 접근이 가능하다.
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	
	public String getLogdate() {
		return logdate;
	}
	public void setLogdate(String logdate) {
		this.logdate = logdate;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public BoardBean() {
		super();
		
	}
	
	
	
	
}