package com.fortech.service;

import javax.ejb.Stateless;

import com.fortech.model.InterpretationRule;

@Stateless
public class Inter extends RuleService<InterpretationRule>{
	public Inter(){
		super(InterpretationRule.class);
	}
}
