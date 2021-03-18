/**
 * 空字符验证
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
 * 数字字符验证
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
 * 数字字符验证(正负数小数等)
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
 * 数字字符验证
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
 * 数字字符验证
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
 * 字符验证
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
 * 字符验证
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
 * 页码验证
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
 * 关键字验证
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
 * 版本号验证
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
 * 货币与利率验证
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
 * 货币与利率验证
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
 * 货币格式化
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
 * 货币格式化还原
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