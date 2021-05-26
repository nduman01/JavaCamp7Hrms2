package com.dumannazim.hrms2.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dumannazim.hrms2.business.abstracts.JobPositionService;
import com.dumannazim.hrms2.core.utilities.results.DataResult;
import com.dumannazim.hrms2.core.utilities.results.ErrorResult;
import com.dumannazim.hrms2.core.utilities.results.Result;
import com.dumannazim.hrms2.core.utilities.results.SuccessDataResult;
import com.dumannazim.hrms2.core.utilities.results.SuccessResult;
import com.dumannazim.hrms2.dataAccess.abstracts.JobPositionDao;
import com.dumannazim.hrms2.entities.concretes.JobPosition;

@Service
public class JobPositionManager implements JobPositionService {

	private final JobPositionDao jobPositionDao;
	
	@Autowired
	public JobPositionManager(JobPositionDao jobPositionDao) {
		this.jobPositionDao = jobPositionDao;
	}

	@Override
	public DataResult<List<JobPosition>> getAll() {
		return new SuccessDataResult<>(this.jobPositionDao.findAll());
	}

	@Override
	public DataResult<List<JobPosition>> findByPositionIs(String position) {
		return new SuccessDataResult<>(this.jobPositionDao.findByPositionIs(position));
	}

	@Override
	public Result add(JobPosition jobPosition) {
		if (this.findByPositionIs(jobPosition.getPosition()).getData().size() != 0){
			return new ErrorResult("This job position already exists");
		}

		this.jobPositionDao.save(jobPosition);
		return new SuccessResult("Process succeeded");
	}
}
