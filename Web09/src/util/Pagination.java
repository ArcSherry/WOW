package util;

public class Pagination {

	private int pageNum;// һҳ��ʾ������;
	private int yeMa;// ��ʾ����ҳ�룬��1����5,1����10
	// ���㿪ʼҳ�ͽ���ҳ
	private int begin;
	private int end;
	// ��ǰҳ
	private int ye;
	// ���ҳ
	private int maxYe;

	public Pagination(int ye, int total, int pageNum, int yeMa) {
		this.ye = ye;
		this.pageNum = pageNum;
		this.yeMa = yeMa;
		cal(total);
	}

	public void cal(int total) {
		// ��֤��С��ҳ�ǵ�һҳ
		if (ye < 1) {
			ye = 1;
		}
	
		// ��������¼���������ҳ��
		maxYe = total % pageNum == 0 ? total / pageNum : total / pageNum + 1;

		// ��֤����ҳ�����һҳ
		if (ye > maxYe) {
			ye = maxYe;
		}

		// �õ�ǰѡ��ҳ����ʾ���м�
		begin = ye - yeMa / 2;
		end = ye + yeMa / 2;
		// ����ҳ����1,2����r
		if (begin < 1) {
			begin = 1;
			end = yeMa;
		}
		// ����ҳ���ǵ���1,2����r
		if (end > maxYe) {
			end = maxYe;
			begin = end - yeMa + 1;
		}

		// �������ҳ������5�����
		if (maxYe < yeMa) {
			begin = 1;
			end = maxYe;
		}
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getYeMa() {
		return yeMa;
	}

	public void setYeMa(int yeMa) {
		this.yeMa = yeMa;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getYe() {
		return ye;
	}

	public void setYe(int ye) {
		this.ye = ye;
	}

	public int getMaxYe() {
		return maxYe;
	}

	public void setMaxYe(int maxYe) {
		this.maxYe = maxYe;
	}

}