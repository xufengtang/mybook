package cn.ewb.cq.com;

import java.util.ArrayList;

public class AutoArrayList extends ArrayList {
	private Class itemBean;

	public AutoArrayList(Class itemBean) {
		this.itemBean = itemBean;
	}

	public Object get(int index) {
		try {
			while (index >= size()) {
				add(itemBean.newInstance());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.get(index);
	}
}
