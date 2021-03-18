function getCookie(name)
{								// ʹ�����Ʋ���ȡ��Cookieֵ, null��ʾCookie������
  var strCookies = document.cookie;
  var cookieName = name + "=";  // Cookie����
  var valueBegin, valueEnd, value;
  // Ѱ���Ƿ��д�Cookie����
  valueBegin = strCookies.indexOf(cookieName);
  if (valueBegin == -1) return null;  // û�д�Cookie
  // ȡ��ֵ�Ľ�βλ��
  valueEnd = strCookies.indexOf(";", valueBegin);
  if (valueEnd == -1)
      valueEnd = strCookies.length;  // ����һ��Cookie
  // ȡ��Cookieֵ
  value = strCookies.substring(valueBegin+cookieName.length,valueEnd);
  return value;
}//end function getCookie

function saveCookie(name, value, expires, path, domain, secure)
{											// ����Cookie
  var strCookie = name + "=" + value;
  if (expires){								// ����Cookie������, ����Ϊ����
     var curTime = new Date();
     curTime.setTime(curTime.getTime() + expires*24*60*60*1000);
     strCookie += "; expires=" + curTime.toGMTString();
  }
  // Cookie��·��
  strCookie +=  (path) ? "; path=" + path : ""; 
  // Cookie��Domain
  strCookie +=  (domain) ? "; domain=" + domain : "";
  // �Ƿ���Ҫ���ܴ���,Ϊһ������ֵ
  strCookie +=  (secure) ? "; secure" : "";
  document.cookie = strCookie;
}

function checkCookieExist(name)
{											// ���Cookie�Ƿ����
	if (getCookie(name))
		return true;
	else
		return false;
}//end function checkCookieExist

function deleteCookie(name, path, domain)
{											// ɾ��Cookie
	var strCookie;
	if (checkCookieExist(name))
	{										    // ����Cookie������Ϊ������
		strCookie = name + "="; 
		strCookie += (path) ? "; path=" + path : "";
		strCookie += (domain) ? "; domain=" + domain : "";
		strCookie += "; expires=Thu, 01-Jan-70 00:00:01 GMT";
		document.cookie = strCookie;
	}//end if
}//end function deleteCookie