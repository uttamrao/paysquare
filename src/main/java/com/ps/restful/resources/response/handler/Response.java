package com.ps.restful.resources.response.handler;

import java.util.List;

import com.ps.dto.DTO;

public interface Response{

	public DTO getData();
	

	public void setData(DTO data) ;
	

	public List<DTO> getErrors() ;
	

	public void setErrors(List<DTO> error) ;
	

	public DTO getMeta() ;
	

	public void setMeta(DTO meta) ;
}
