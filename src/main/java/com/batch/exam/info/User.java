package com.batch.exam.info;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="USER")
public  class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	Long id;
	/** 아이디 */
	@Column(name="USER_ID", unique = true, nullable = false)
	String userId;
	/** 이름 */
	@Column(name="USER_NAME", nullable = true)
	String userName;
	/** 핸드폰 */
	@Column(name="PHONE", nullable = true)
	String phone;
	/** 등록일 */
	@Column(name="REG_DATE", nullable = true)
	Date regDate;
	
	
	public User( Map<String, Object> param ) throws ParseException{
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.userId = param.get("USER_ID").toString();
		this.userName = param.get("USER_NAME").toString();
		this.phone = param.get("PHONE").toString();
//		if ( !ObjectUtils.isEmpty(param.get("regDate")) ) {
//			this.regDate = format.parse(param.get("regDate"));
//		}
	}
	
}
