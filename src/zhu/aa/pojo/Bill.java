package zhu.aa.pojo;

/**
 * 账单的pojo类 (javabean)
 * 
 * @author think
 *
 */
public class Bill {
	// 服务号
	private String SERVICE_TRADE_ID;
	// 求救时间
	private String RESCUE_TIME;
	// 用户名称
	private String USER_NAME;

	@Override
	public String toString() {
		return "Bill [SERVICE_TRADE_ID=" + SERVICE_TRADE_ID + ", RESCUE_TIME=" + RESCUE_TIME + ", USER_NAME="
				+ USER_NAME + ", NAME_S=" + NAME_S + ", CITY=" + CITY + ", AREAS=" + AREAS + ", COUNTRY=" + COUNTRY
				+ ", LICENSE=" + LICENSE + ", CASEDESC=" + CASEDESC + ", STATUS_DETAIL=" + STATUS_DETAIL
				+ ", COMFIRMED_FEE=" + COMFIRMED_FEE + ", TRAIL_RANGE_KM=" + TRAIL_RANGE_KM + ", OUTER_RANGE_KM="
				+ OUTER_RANGE_KM + ", FEE_TYPE_DESC=" + FEE_TYPE_DESC + "]";
	}

	// 所属机构
	private String NAME_S;
	// 省
	private String CITY;
	// 市
	private String AREAS;
	// 县
	private String COUNTRY;
	// 车牌
	private String LICENSE;
	// 故障类型
	private String CASEDESC;
	// 救援结果
	private String STATUS_DETAIL;
	// 小计金额
	private String COMFIRMED_FEE;
	// 拖车公里数
	private String TRAIL_RANGE_KM;
	// 超范围补助公里数
	private String OUTER_RANGE_KM;
	// 费用类型
	private String FEE_TYPE_DESC;
	// 确认金额
	private String o;
	// 到达里程
	private String p;
	// 牵引里程
	private String q;
	// 过路费
	private String r;
	// 应结算金额
	private String s;
	// 差额
	private String t;
	// 接电
	private String u;
	// 换胎
	private String v;
	// 送油
	private String w;
	// 拖车
	private String x;
	// 困境
	private String y;
	// 照片审核状态
	private String PIC_AUDIT_STATE;
	// 备注
	private String aa;
	//服务商名字
	private String name;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Bill() {
		super();
	}

	public String getSERVICE_TRADE_ID() {
		return SERVICE_TRADE_ID;
	}

	public void setSERVICE_TRADE_ID(String sERVICE_TRADE_ID) {
		SERVICE_TRADE_ID = sERVICE_TRADE_ID;
	}

	public String getRESCUE_TIME() {
		return RESCUE_TIME;
	}

	public void setRESCUE_TIME(String rESCUE_TIME) {
		RESCUE_TIME = rESCUE_TIME;
	}

	public String getUSER_NAME() {
		return USER_NAME;
	}

	public void setUSER_NAME(String uSER_NAME) {
		USER_NAME = uSER_NAME;
	}

	public String getNAME_S() {
		return NAME_S;
	}

	public void setNAME_S(String nAME_S) {
		NAME_S = nAME_S;
	}

	public String getCITY() {
		return CITY;
	}

	public void setCITY(String cITY) {
		CITY = cITY;
	}

	public String getAREAS() {
		return AREAS;
	}

	public void setAREAS(String aREAS) {
		AREAS = aREAS;
	}

	public String getCOUNTRY() {
		return COUNTRY;
	}

	public void setCOUNTRY(String cOUNTRY) {
		COUNTRY = cOUNTRY;
	}

	public String getLICENSE() {
		return LICENSE;
	}

	public void setLICENSE(String lICENSE) {
		LICENSE = lICENSE;
	}

	public String getCASEDESC() {
		return CASEDESC;
	}

	public void setCASEDESC(String cASEDESC) {
		CASEDESC = cASEDESC;
	}

	public String getSTATUS_DETAIL() {
		return STATUS_DETAIL;
	}

	public void setSTATUS_DETAIL(String sTATUS_DETAIL) {
		STATUS_DETAIL = sTATUS_DETAIL;
	}

	public String getCOMFIRMED_FEE() {
		return COMFIRMED_FEE;
	}

	public void setCOMFIRMED_FEE(String cOMFIRMED_FEE) {
		COMFIRMED_FEE = cOMFIRMED_FEE;
	}

	public String getTRAIL_RANGE_KM() {
		return TRAIL_RANGE_KM;
	}

	public void setTRAIL_RANGE_KM(String tRAIL_RANGE_KM) {
		TRAIL_RANGE_KM = tRAIL_RANGE_KM;
	}

	public String getOUTER_RANGE_KM() {
		return OUTER_RANGE_KM;
	}

	public void setOUTER_RANGE_KM(String oUTER_RANGE_KM) {
		OUTER_RANGE_KM = oUTER_RANGE_KM;
	}

	public String getFEE_TYPE_DESC() {
		return FEE_TYPE_DESC;
	}

	public void setFEE_TYPE_DESC(String fEE_TYPE_DESC) {
		FEE_TYPE_DESC = fEE_TYPE_DESC;
	}

	public String getO() {
		return o;
	}

	public void setO(String o) {
		this.o = o;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public String getR() {
		return r;
	}

	public void setR(String r) {
		this.r = r;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public String getU() {
		return u;
	}

	public void setU(String u) {
		this.u = u;
	}

	public String getV() {
		return v;
	}

	public void setV(String v) {
		this.v = v;
	}

	public String getW() {
		return w;
	}

	public void setW(String w) {
		this.w = w;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}


	public String getPIC_AUDIT_STATE() {
		return PIC_AUDIT_STATE;
	}

	public void setPIC_AUDIT_STATE(String pIC_AUDIT_STATE) {
		PIC_AUDIT_STATE = pIC_AUDIT_STATE;
	}

	public String getAa() {
		return aa;
	}

	public void setAa(String aa) {
		this.aa = aa;
	}

}
