package com.revature.reimbursement;

public enum Category {
	lodging, travel, food, other;


public static Category stringToCat(String s) {
	switch(s) {
		case "lodging": return Category.lodging;
		case "travel":return Category.travel;
		case "food": return Category.food;
		default: return Category.other;
	}
}
}