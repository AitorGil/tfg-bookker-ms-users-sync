package com.aitorgc.users.api.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.NonNull;

/**
 * 
 * @author Aitor Gil Callejo
 *
 */
@Entity
@Table(name = "c_user_extra_field")
public class UserExtraFieldEntity extends BaseEntity {

	private static final long serialVersionUID = -5844092832768533931L;

	@NonNull
	@AttributeOverride(name = "id", column = @Column(name = "user_id"))
	private UserId userId;

	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	private UserEntity user;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_1;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_2;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_3;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_4;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_5;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_6;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_7;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_8;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_9;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_10;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_11;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_12;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_13;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_14;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_15;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_16;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_17;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_18;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_19;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_20;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_21;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_22;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_23;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_24;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_25;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_26;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_27;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_28;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_29;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_30;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_31;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_32;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_33;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_34;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_35;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_36;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_37;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_38;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_39;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_40;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_41;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_42;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_43;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_44;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_45;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_46;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_47;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_48;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_49;

	@Size(max = 255)
	@Column(length = 255)
	private String extra_50;

	public UserExtraFieldEntity() {
	}

	public UserExtraFieldEntity(UserId userId) {
		this.userId = userId;
	}

	public void updateField(Integer fieldNumber, String fieldValue) {
		switch (fieldNumber) {
		case 1:
			this.setExtra_1(fieldValue);
			break;
		case 2:
			this.setExtra_2(fieldValue);
			break;
		case 3:
			this.setExtra_3(fieldValue);
			break;
		case 4:
			this.setExtra_4(fieldValue);
			break;
		case 5:
			this.setExtra_5(fieldValue);
			break;
		case 6:
			this.setExtra_6(fieldValue);
			break;
		case 7:
			this.setExtra_7(fieldValue);
			break;
		case 8:
			this.setExtra_8(fieldValue);
			break;
		case 9:
			this.setExtra_9(fieldValue);
			break;
		case 10:
			this.setExtra_10(fieldValue);
			break;
		case 11:
			this.setExtra_11(fieldValue);
			break;
		case 12:
			this.setExtra_12(fieldValue);
			break;
		case 13:
			this.setExtra_13(fieldValue);
			break;
		case 14:
			this.setExtra_14(fieldValue);
			break;
		case 15:
			this.setExtra_15(fieldValue);
			break;
		case 16:
			this.setExtra_16(fieldValue);
			break;
		case 17:
			this.setExtra_17(fieldValue);
			break;
		case 18:
			this.setExtra_18(fieldValue);
			break;
		case 19:
			this.setExtra_19(fieldValue);
			break;
		case 20:
			this.setExtra_20(fieldValue);
			break;
		case 21:
			this.setExtra_21(fieldValue);
			break;
		case 22:
			this.setExtra_22(fieldValue);
			break;
		case 23:
			this.setExtra_23(fieldValue);
			break;
		case 24:
			this.setExtra_24(fieldValue);
			break;
		case 25:
			this.setExtra_25(fieldValue);
			break;
		case 26:
			this.setExtra_26(fieldValue);
			break;
		case 27:
			this.setExtra_27(fieldValue);
			break;
		case 28:
			this.setExtra_28(fieldValue);
			break;
		case 29:
			this.setExtra_29(fieldValue);
			break;
		case 30:
			this.setExtra_30(fieldValue);
			break;
		case 31:
			this.setExtra_31(fieldValue);
			break;
		case 32:
			this.setExtra_32(fieldValue);
			break;
		case 33:
			this.setExtra_33(fieldValue);
			break;
		case 34:
			this.setExtra_34(fieldValue);
			break;
		case 35:
			this.setExtra_35(fieldValue);
			break;
		case 36:
			this.setExtra_36(fieldValue);
			break;
		case 37:
			this.setExtra_37(fieldValue);
			break;
		case 38:
			this.setExtra_38(fieldValue);
			break;
		case 39:
			this.setExtra_39(fieldValue);
			break;
		case 40:
			this.setExtra_40(fieldValue);
			break;
		case 41:
			this.setExtra_41(fieldValue);
			break;
		case 42:
			this.setExtra_42(fieldValue);
			break;
		case 43:
			this.setExtra_43(fieldValue);
			break;
		case 44:
			this.setExtra_44(fieldValue);
			break;
		case 45:
			this.setExtra_45(fieldValue);
			break;
		case 46:
			this.setExtra_46(fieldValue);
			break;
		case 47:
			this.setExtra_47(fieldValue);
			break;
		case 48:
			this.setExtra_48(fieldValue);
			break;
		case 49:
			this.setExtra_49(fieldValue);
			break;
		case 50:
			this.setExtra_50(fieldValue);
			break;
		default:
			throw new UnsupportedOperationException("No se contempla el campo extra con el número " + fieldNumber);
		}
	}

	public UserId getUserId() {
		return userId;
	}

	public void setUserId(UserId userId) {
		this.userId = userId;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getExtra_1() {
		return extra_1;
	}

	public void setExtra_1(String extra_1) {
		this.extra_1 = extra_1;
	}

	public String getExtra_2() {
		return extra_2;
	}

	public void setExtra_2(String extra_2) {
		this.extra_2 = extra_2;
	}

	public String getExtra_3() {
		return extra_3;
	}

	public void setExtra_3(String extra_3) {
		this.extra_3 = extra_3;
	}

	public String getExtra_4() {
		return extra_4;
	}

	public void setExtra_4(String extra_4) {
		this.extra_4 = extra_4;
	}

	public String getExtra_5() {
		return extra_5;
	}

	public void setExtra_5(String extra_5) {
		this.extra_5 = extra_5;
	}

	public String getExtra_6() {
		return extra_6;
	}

	public void setExtra_6(String extra_6) {
		this.extra_6 = extra_6;
	}

	public String getExtra_7() {
		return extra_7;
	}

	public void setExtra_7(String extra_7) {
		this.extra_7 = extra_7;
	}

	public String getExtra_8() {
		return extra_8;
	}

	public void setExtra_8(String extra_8) {
		this.extra_8 = extra_8;
	}

	public String getExtra_9() {
		return extra_9;
	}

	public void setExtra_9(String extra_9) {
		this.extra_9 = extra_9;
	}

	public String getExtra_10() {
		return extra_10;
	}

	public void setExtra_10(String extra_10) {
		this.extra_10 = extra_10;
	}

	public String getExtra_11() {
		return extra_11;
	}

	public void setExtra_11(String extra_11) {
		this.extra_11 = extra_11;
	}

	public String getExtra_12() {
		return extra_12;
	}

	public void setExtra_12(String extra_12) {
		this.extra_12 = extra_12;
	}

	public String getExtra_13() {
		return extra_13;
	}

	public void setExtra_13(String extra_13) {
		this.extra_13 = extra_13;
	}

	public String getExtra_14() {
		return extra_14;
	}

	public void setExtra_14(String extra_14) {
		this.extra_14 = extra_14;
	}

	public String getExtra_15() {
		return extra_15;
	}

	public void setExtra_15(String extra_15) {
		this.extra_15 = extra_15;
	}

	public String getExtra_16() {
		return extra_16;
	}

	public void setExtra_16(String extra_16) {
		this.extra_16 = extra_16;
	}

	public String getExtra_17() {
		return extra_17;
	}

	public void setExtra_17(String extra_17) {
		this.extra_17 = extra_17;
	}

	public String getExtra_18() {
		return extra_18;
	}

	public void setExtra_18(String extra_18) {
		this.extra_18 = extra_18;
	}

	public String getExtra_19() {
		return extra_19;
	}

	public void setExtra_19(String extra_19) {
		this.extra_19 = extra_19;
	}

	public String getExtra_20() {
		return extra_20;
	}

	public void setExtra_20(String extra_20) {
		this.extra_20 = extra_20;
	}

	public String getExtra_21() {
		return extra_21;
	}

	public void setExtra_21(String extra_21) {
		this.extra_21 = extra_21;
	}

	public String getExtra_22() {
		return extra_22;
	}

	public void setExtra_22(String extra_22) {
		this.extra_22 = extra_22;
	}

	public String getExtra_23() {
		return extra_23;
	}

	public void setExtra_23(String extra_23) {
		this.extra_23 = extra_23;
	}

	public String getExtra_24() {
		return extra_24;
	}

	public void setExtra_24(String extra_24) {
		this.extra_24 = extra_24;
	}

	public String getExtra_25() {
		return extra_25;
	}

	public void setExtra_25(String extra_25) {
		this.extra_25 = extra_25;
	}

	public String getExtra_26() {
		return extra_26;
	}

	public void setExtra_26(String extra_26) {
		this.extra_26 = extra_26;
	}

	public String getExtra_27() {
		return extra_27;
	}

	public void setExtra_27(String extra_27) {
		this.extra_27 = extra_27;
	}

	public String getExtra_28() {
		return extra_28;
	}

	public void setExtra_28(String extra_28) {
		this.extra_28 = extra_28;
	}

	public String getExtra_29() {
		return extra_29;
	}

	public void setExtra_29(String extra_29) {
		this.extra_29 = extra_29;
	}

	public String getExtra_30() {
		return extra_30;
	}

	public void setExtra_30(String extra_30) {
		this.extra_30 = extra_30;
	}

	public String getExtra_31() {
		return extra_31;
	}

	public void setExtra_31(String extra_31) {
		this.extra_31 = extra_31;
	}

	public String getExtra_32() {
		return extra_32;
	}

	public void setExtra_32(String extra_32) {
		this.extra_32 = extra_32;
	}

	public String getExtra_33() {
		return extra_33;
	}

	public void setExtra_33(String extra_33) {
		this.extra_33 = extra_33;
	}

	public String getExtra_34() {
		return extra_34;
	}

	public void setExtra_34(String extra_34) {
		this.extra_34 = extra_34;
	}

	public String getExtra_35() {
		return extra_35;
	}

	public void setExtra_35(String extra_35) {
		this.extra_35 = extra_35;
	}

	public String getExtra_36() {
		return extra_36;
	}

	public void setExtra_36(String extra_36) {
		this.extra_36 = extra_36;
	}

	public String getExtra_37() {
		return extra_37;
	}

	public void setExtra_37(String extra_37) {
		this.extra_37 = extra_37;
	}

	public String getExtra_38() {
		return extra_38;
	}

	public void setExtra_38(String extra_38) {
		this.extra_38 = extra_38;
	}

	public String getExtra_39() {
		return extra_39;
	}

	public void setExtra_39(String extra_39) {
		this.extra_39 = extra_39;
	}

	public String getExtra_40() {
		return extra_40;
	}

	public void setExtra_40(String extra_40) {
		this.extra_40 = extra_40;
	}

	public String getExtra_41() {
		return extra_41;
	}

	public void setExtra_41(String extra_41) {
		this.extra_41 = extra_41;
	}

	public String getExtra_42() {
		return extra_42;
	}

	public void setExtra_42(String extra_42) {
		this.extra_42 = extra_42;
	}

	public String getExtra_43() {
		return extra_43;
	}

	public void setExtra_43(String extra_43) {
		this.extra_43 = extra_43;
	}

	public String getExtra_44() {
		return extra_44;
	}

	public void setExtra_44(String extra_44) {
		this.extra_44 = extra_44;
	}

	public String getExtra_45() {
		return extra_45;
	}

	public void setExtra_45(String extra_45) {
		this.extra_45 = extra_45;
	}

	public String getExtra_46() {
		return extra_46;
	}

	public void setExtra_46(String extra_46) {
		this.extra_46 = extra_46;
	}

	public String getExtra_47() {
		return extra_47;
	}

	public void setExtra_47(String extra_47) {
		this.extra_47 = extra_47;
	}

	public String getExtra_48() {
		return extra_48;
	}

	public void setExtra_48(String extra_48) {
		this.extra_48 = extra_48;
	}

	public String getExtra_49() {
		return extra_49;
	}

	public void setExtra_49(String extra_49) {
		this.extra_49 = extra_49;
	}

	public String getExtra_50() {
		return extra_50;
	}

	public void setExtra_50(String extra_50) {
		this.extra_50 = extra_50;
	}

}
