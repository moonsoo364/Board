function writeCheck()
{
	var form =document.updateform;
	if(!form.title.value)
	{
		alert("제목을 적어주세요");
		form.title.focus();
		return;
	}
	else if(!form.content.value)
	{
		alert("내용을 적어주세요"); 
		form.content.focus();
		return;
	}
	else
	{
		document.updateform.submit();
	}
	
	
	
}