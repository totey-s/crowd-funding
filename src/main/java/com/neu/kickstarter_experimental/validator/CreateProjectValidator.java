package com.neu.kickstarter_experimental.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.kickstarter_experimental.pojo.CreatedProject;
import com.neu.kickstarter_experimental.pojo.User;

public class CreateProjectValidator implements Validator{

	@Override
	public boolean supports(Class<?> aClass) {
		return aClass.equals(CreatedProject.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		CreatedProject createdProject = (CreatedProject)obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "projectName", "error.invalid.createdProject", "Project Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "projectPic", "error.invalid.createdProject", "Project Image Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.invalid.createdProject", "Description Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "location", "error.invalid.createdProject", "Location Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "error.invalid.createdProject", "Select a Category");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fundGoal", "error.invalid.createdProject", "Fund Goal Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "duration", "error.invalid.createdProject", "Fund Duration Required");
		
	}

}
