package com.revature.util;

import com.revature.models.AnimalsClientMessage;

public class AnimalsClientMessageUtil {
	public static final AnimalsClientMessage CREATION_SUCCESSFUL = new AnimalsClientMessage("REGISTATION SUCCESSFUL");
	public static final AnimalsClientMessage CREATION_FAILED = new AnimalsClientMessage("SOMETHING WENT WRONG DURING CREATION");
	public static final AnimalsClientMessage UPDATE_SUCCESSFUL = new AnimalsClientMessage("UPDATE SUCCESSFUL");
	public static final AnimalsClientMessage UPDATE_FAILED = new AnimalsClientMessage("SOMETHING WENT WRONG DURING UPDATE");
	public static final AnimalsClientMessage DELETION_SUCCESSFUL = new AnimalsClientMessage("DELETION SUCCESSFUL");
	public static final AnimalsClientMessage DELETION_FAILED = new AnimalsClientMessage("SOMETHING WENT WRONG DURING DELETION");
}