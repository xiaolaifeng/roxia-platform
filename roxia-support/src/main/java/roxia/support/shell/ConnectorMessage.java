package roxia.support.shell;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class ConnectorMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5409787665914036725L;
	public static final String MESSAGE_CONTENT_TYPE_XML = "xml";
	public static final String MESSAGE_CONTENT_TYPE_JSON = "json";

	public final static int PROCESS_STATUS_PROCESSING = 1;
	public final static int PROCESS_STATUS_FINISHED = 2;


	private Long id;
	private String platformCode;
	private String interfaceCode;
	private String messageContentType;
	private Boolean isCompress = Boolean.FALSE;
	private String messageContent;
	private Date createTime = Calendar.getInstance().getTime();
	private String confirmId;
	private String fileName;
	private Integer processStatus = PROCESS_STATUS_PROCESSING;
	private Date processTime;
	private Date confirmTime = new Date();
	private String source;
	private Integer receiveMsgSize;
	private Integer sendMsgSize;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlatformCode() {
		return platformCode;
	}

	public void setPlatformCode(String platformCode) {
		this.platformCode = platformCode;
	}

	public String getInterfaceCode() {
		return interfaceCode;
	}

	public void setInterfaceCode(String interfaceCode) {
		this.interfaceCode = interfaceCode;
	}

	public String getMessageContentType() {
		return messageContentType;
	}

	public void setMessageContentType(String messageContentType) {
		this.messageContentType = messageContentType;
	}

	public Boolean getIsCompress() {
		return isCompress;
	}

	public void setIsCompress(Boolean isCompress) {
		this.isCompress = isCompress;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getConfirmId() {
		return confirmId;
	}

	public void setConfirmId(String confirmId) {
		this.confirmId = confirmId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(Integer processStatus) {
		this.processStatus = processStatus;
	}

	public Date getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getReceiveMsgSize() {
        return receiveMsgSize;
    }

    public void setReceiveMsgSize(Integer receiveMsgSize) {
        this.receiveMsgSize = receiveMsgSize;
    }

    public Integer getSendMsgSize() {
        return sendMsgSize;
    }

    public void setSendMsgSize(Integer sendMsgSize) {
        this.sendMsgSize = sendMsgSize;
    }

}
