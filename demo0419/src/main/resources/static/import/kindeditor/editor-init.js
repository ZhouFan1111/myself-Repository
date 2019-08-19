var ke_tools = [ 'undo', 'redo','plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright','justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript','superscript', '|', 'selectall', '-','title', 'fontname', 'fontsize', '|', 'textcolor', 'bgcolor', 'bold','italic', 'underline', 'strikethrough', 'removeformat', '|' ,'hr', 'link', 'unlink'];
KE.init({
		id : 'txt_content',
		items:ke_tools,
		filterMode:true,
		resizeMode:0,
		newlineTag:"br"
	});