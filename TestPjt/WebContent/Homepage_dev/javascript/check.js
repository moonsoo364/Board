
function CheckAll(){
	if(!checkId(register_form.id.value)){
		return false;
	}
	if(!checkPassword(register_form.id.value,register_form.pwd.value,register_form.repwd.value)){
		return false;
	}
	if(!checkName(register_form.name.value)){
		return false;
	}
	if(!checkBirthday(register_form.birthday.value)){
		return false;
	}
	if(!checkEmail(register_form.email.value)){
		return false;
	}
	if(!checkAddress(register_form.address.value)){
		return false;
	}
	if(!checkDetailaddress(register_form.detailaddress.value)){
		return false;
	}

	
	//끝으로 데이터베이스에 입력 값 전송
 	document.register_form.submit();
    return true;
	
}

//join class로 묶고 ex) join.isbirthday
//생년월일 확인 함수
function isbirthday(value){
 	//생년월일 변수 선언 
 	var year = Number(value.substr(0,2));// 입력한 자리의 0~1까지 연
 	var month =Number(value.substr(2,2));// 입력한 자리의 2~3까지 월
 	var day =Number(value.substr(4,2));// 입력한 자리의 4~5까지 일

 	if (month >12 || month<1){
 		// 월은 1~12월 까지 있다.
 		alert("월은 01~12 사이의 수 입니다.");
 		return false;
 	}
 	else if (day<1||day>31){
		//일은 1~31일 까지 있다.
		alert("일은 01~31을 입력해주세요.");
		return false;
	}
	else if ((month==4||month==6||month==9||month==11)&& day==31){
	 	//4,6,9,11월달은 30일 까지 있다
	 	alert("4,6,9,11월 달은 30일 까지 있습니다");
		return false;
	}
	else if (month==2){
		var leap=(year %4 ==0);
		
		if(day>29 || (day==29 && !leap)){
			//4년에 한 번 2월 29일이 존재한다
			alert("2월달은 28,29일 까지 있습니다");
			return false;	
		}
	
 	else 
 		return true;

	}
	return true;
}

//공백 확인 함수
	function checkExistData(value,dataName){
		if(value == ""){
			alert(dataName + " 입력 해주세요!");
			return false;
	}
			return true;
}

//아이디 유효성 검사
	function checkId(id) {
        //Id가 입력되었는지 확인하기
        if (!checkExistData(id, "아이디를"))
            return false;
        var idRegExp =/^[a-zA-z0-9]{5,20}$/;
        
        if(!idRegExp.test(id)) {
        	alert("아이디는 영문 대소문자와 숫자 5~20자리로 입력해야합니다!");
     		register_form.id.value="";
     		register_form.id.focus;
 			return false;
        	}
        return true;
     }
    		
 //비밀번호 유효성 검사
 	function checkPassword(id, pwd, repwd) {
        //비밀번호가 입력되었는지 확인하기
        if (!checkExistData(pwd, "비밀번호를"))
            return false;
        //비밀번호 확인이 입력되었는지 확인하기
        if (!checkExistData(repwd, "비밀번호 확인을"))
            return false;
        	var pwdRegExp = /^[a-zA-z0-9]{8,16}$/; //비밀번호 유효성 검사
        if (!pwdRegExp.test(pwd)) {
            alert("비밀번호는 영문 대소문자와 숫자 8~16자리로 입력해야합니다!");
            register_form.pwd.value = "";
            register_form.pwd.focus();
            return false;
        }
        
        //비밀번호와 비밀번호확인이 맞지 않는 경우
        if (pwd != repwd) {
            alert("두 비밀번호가 맞지 않습니다.");
            register_form.pwd.value = "";
            register_form.repwd.value = "";
            register_form.repwd.focus();
            return false;
        }
 
        //아이디와 비밀번호가 같을 경우
        if (id == pwd) {
            alert("아이디와 비밀번호는 같을 수 없습니다!");
            register_form.pwd.value = "";
            register_form.repwd.value = "";
            form.repwd.focus();
            return false;
        }
        return true; //확인이 완료되었을 때
    }

 	function checkName(name){
 	 //이름이 입력되었는지 확인하기
        if (!checkExistData(name, "이름을")){
        	register_form.name.focus();
            return false;
 		}
 			return true;
 }
  
 	function checkBirthday(birthday){
 	//생년월일이 입력되었는지 확인하기
 		var birthdayRegExp =/^[0-9]{6}$/;
        if (!checkExistData(birthday, "생년월일을")){
        	register_form.birthday.focus();
            return false;
 		}
 	//생년월일은 6자리 숫자!
        if(!birthdayRegExp.test(birthday)) {
       		 alert("생년월일은 6자리로 입력해야됩니다!");
	     	register_form.birthday.value="";
	     	register_form.birthday.focus;
	     	return false;
     	}
     //월(1~12)일(1~31)범위 제한하기
     	if(!isbirthday(birthday)){
     		register_form.birthday.value="";
	     	register_form.birthday.focus;
	     	return false;
     	}
     	
 		return true;
 	}
	function checkEmail(email){
		//이메일이 입력되었는 지 확인하기
		if(!checkExistData(email,"이메일을")){
			register_form.email.focus();
			return false;
		}
		//이메일 양식
		var exptext = /^[A-Za-z0-9\-_]+@[A-Za-z0-9\-_]+\.[A-Za-z0-9\-]+/;
		//  1.'/' 안에 있는 내용은 정규표현식 검증에 사용되는 패턴이 이 안에 위치함
		//  2.'^' 표시는 처음 시작하는 부분부터 일치한다는 표시이다.
		//	3.'[0-9a-zA-Z\-_]' 하나의 문자가 []안에 위치한 규칙을 따른다는 뜻이다, 
		//	0-9a-zA-Z는 숫자와 영어 대소문자를 사용한다는 뜻이고 _는 언더바,하이픈 앞에 백슬래쉬(\)를 붙이는 이유는  
		//	하이픈이 범위 지정 기호로 쓰이기 때문에 이스케이프 처리를 해서 지정기호가 아닌 별개의 문자로 처리 하게끔한다.
		//	+표시는 그 조건에 해당하는 문자열이 하나는 있어야함을 의미합니다.
		//	4. '+@' 골뱅이는 첫번째 문자와 두번째 문자사이에 항상 있어야한다.
		//	5. '\.' '.'은 두번째 문자와 세번째 문자 사이에 항상있어야한다.  
		
		 
				if(!exptext.test(email)){

			//이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐경우			

			alert("이메일 형식이 올바르지 않습니다.");
			register_form.email.focus();
			return false;

		}

	return true

	}
	function checkAddress(address) {
		//주소가 입력 되었는 지 확인
		if(!checkExistData(address,"주소를")){
			register_form.address.focus();
			return false;
		}
		return true;
	}
	function checkDetailaddress(detailaddress) {
		//상세 주소를 입력했는 지 확인
		if(!checkExistData(detailaddress,"상세주소를")){
			register_form.detailaddress.focus();
			return false;
		}
		return true;
	}
	
	
	
 
 



