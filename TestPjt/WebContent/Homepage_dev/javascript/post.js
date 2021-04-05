function writeCheck()
{
	var form =document.postform;
	if(!form.title.value)
	{
		alert("제목을 적어주세요");
		form.title.focus();
		return;
	}
	if(!form.content.value)
	{
		alert("내용을 적어주세요"); 
		form.content.focus();
		return;
	}
	
	
}