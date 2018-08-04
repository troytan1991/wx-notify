package com.troytan.notify.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.troytan.notify.domain.Customer;
import com.troytan.notify.domain.News;
import com.troytan.notify.domain.Worker;
import com.troytan.notify.dto.CustomerDto;
import com.troytan.notify.dto.DesignerDto;
import com.troytan.notify.dto.FitmentDto;

public interface CmzyService {

    List<News> searchNews(String searchStr, Page<?> page);

	List<DesignerDto> getDesigners();

	List<Worker> getWorkers();

	List<FitmentDto> getFitments();

	Customer createCustomers(CustomerDto customerDto);

	News getNews(String mediaId);
	
}
