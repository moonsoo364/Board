function inputCheck(){
	if(document.register_form.id.value==""){
		alert("아이디를 입력해 주세요.");
		document.regFrm.id.focus();
		return;
	}
	
	if(document.register_form.pwd.value==""){
		alert("비밀번호를 입력해 주세요.");
		document.regFrm.pwd.focus();
		return;
	}
	if(document.register_form.repwd.value==""){
		alert("비밀번호를 확인해 주세요");
		document.regFrm.repwd.focus();
		return;
	}
	if(document.register_form.pwd.value != document.register_form.repwd.value){
		alert("비밀번호가 일치하지 않습니다.");
		document.regFrm.repwd.value="";
		document.regFrm.repwd.focus();
		return;
	}
	
    if(document.register_form.zipcode.value==""){
		alert("우편번호를 검색해 주세요.");
		return;
	}
	if(document.register_form.detailaddress.value==""){
		alert("상세주소를 입력해 주세요.");
		return;
	}

	document.register_form.submit();
}

function win_close(){
	self.close();
}