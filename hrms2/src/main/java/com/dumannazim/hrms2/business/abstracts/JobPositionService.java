package com.dumannazim.hrms2.business.abstracts;

import com.dumannazim.hrms2.core.utilities.results.Result;
import com.dumannazim.hrms2.entities.concretes.JobPosition;

import java.util.List;

import com.dumannazim.hrms2.core.utilities.results.DataResult;

public interface JobPositionService {
	DataResult<List<JobPosition>> getAll();
	DataResult<List<JobPosition>>  findByPositionIs(String position);

	Result add(JobPosition jobPosition);
}
