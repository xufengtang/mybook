/**
 * ���ַ���֤
 * 
 * **/
function isNull(str){
	var result = false;
	if(str==null||str==""){
		result = true;
	}
	return result;
}

/**
 * �����ַ���֤
 * 
 * **/
function isNotNum(str){
	var result = false;
	var reg =  /^\d+$/;
	if (!reg.test(str))  {               
	   result = true;
	}
	return result;
}

/**
 * �����ַ���֤(������С����)
 * 
 * **/
function isNotNum2(str){
	var result = false;
	if(isNaN(Number(str))){
           result =true
    }	
	return result;
}

/**
 * �����ַ���֤
 * 
 * **/
function isNotNumLen(str,len){
	var result = false;
	var reg = eval("/^\\d{"+ len +"}$/");
	if (!reg.test(str))  {               
	   result = true;
	}
	return result;
}
/**
 * �����ַ���֤
 * 
 * **/
function isNotNumMinMax(str,min,max){
	var result = false;
	var reg = eval("/^\\d{"+ min+ "," + max + "}$/");
	if (!reg.test(str))  {               
	   result = true;
	}
	return result;
}

/**
 * �ַ���֤
 * 
 * **/
function isNotCharMinMax(str,min,max){
	var result = false;
	var reg = eval("/^[A-Za-z0-9]{"+ min+ "," + max + "}$/");
	if (!reg.test(str))  {               
	   result = true;
	}
	return result;
}

/**
 * �ַ���֤
 * 
 * **/
function isNotChar(str){
	var result = false;
	var reg = eval("/^[A-Za-z0-9]+$/");
	if (!reg.test(str))  {               
	   result = true;
	}
	return result;
}

/**
 * ҳ����֤
 * 
 * **/
function isNotPageNum(str){
	var result = false;
	var reg = /^[1-9][0-9]{0,2}$/;
	if (!reg.exec(str))  {               
		result = true;	
	}
	return result;
}

/**
 * �ؼ�����֤
 * 
 * **/
function isNotKeywords(str){
	var result = false;
	var reg = /^\S+($|\,\S+$)/;
	if (!reg.exec(str))  {               
		result = true;	
	}
	return result;
}

/**
 * �汾����֤
 * 
 * **/
function isRightVersion(str){
	var result = false;
	var reg = /^\d+(\d|(\.[0-9]{1,2}))$/;
	if (!reg.exec(str)) {               
		result = true;	
	}
	return result;
}
/**
 * ������������֤
 * 
 * **/
function isNotMoney(str){
	var result = false;
	var reg = /^\d+(\d*|(\.[0-9]{1,2}))$/;
	if (!reg.exec(str)) {               
		result = true;	
	}
	return result;
}

/**
 * ������������֤
 * 
 * **/
function isNotLiLv(str){
	var result = false;
	var reg = /^\d+(\d*|(\.[0-9]{1,4}))$/;
	if (!reg.exec(str)) {               
		result = true;	
	}
	return result;
}


/**
 * ���Ҹ�ʽ��
 * 
 * **/
function fmoney(s, n)   
{   
   n = n > 0 && n <= 20 ? n : 2;   
   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
   var l = s.split(".")[0].split("").reverse(),   
   r = s.split(".")[1];   
   t = "";   
   for(i = 0; i < l.length; i ++ )   
   {   
      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
   }   
   return t.split("").reverse().join("") + "." + r;   
} 

/**
 * ���Ҹ�ʽ����ԭ
 * 
 * **/
function rmoney(s)   
{   
   return parseFloat(s.replace(/[^\d\.-]/g, ""));   
}

function ToUnicode(str){
   return escape(str).toLocaleLowerCase().replace(/%u/gi, '\\u');
}

function ToGB2312(str){
  return unescape(str.replace(/\\u/gi, '%u'));
}