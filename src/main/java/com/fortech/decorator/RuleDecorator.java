package com.fortech.decorator;

import com.fortech.model.BaseRuleModel;

public abstract class RuleDecorator extends BaseRuleModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected BaseRuleModel decoratedRule;
	
	public RuleDecorator(BaseRuleModel decoratedRule) {
		this.decoratedRule = decoratedRule;
	} 
	
	public BaseRuleModel convertRule(){
		
		return null;
	}
}
