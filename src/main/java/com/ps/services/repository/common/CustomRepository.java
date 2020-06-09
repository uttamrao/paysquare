package com.ps.services.repository.common;

public interface CustomRepository<T,ID> {

	void refreshEntity(T t);
}
