/*
 * Copyright 2006-2008 Sun Microsystems, Inc. All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 * 
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2
 * only, as published by the Free Software Foundation.
 * 
 * This code is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 * 
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA
 * 
 * Please contact Sun Microsystems, Inc., 16 Network Circle, Menlo
 * Park, CA 94025 or visit www.sun.com if you need additional
 * information or have any questions.
 */

package com.sun.spot.peripheral.basestation;

import java.io.DataOutputStream;
import java.io.IOException;

import com.sun.spot.peripheral.radio.I802_15_4_MAC;
import com.sun.spot.util.Utils;

public class MLMESetCommand extends MACCommand {
	private int attribute;
	private long value;
	
	public ICommand with(int attribute, long value) {
		this.attribute = attribute;
		this.value = value;
		return this;
	}
	
	protected void writeParametersOnto(DataOutputStream dataOutputStream) throws IOException {
		dataOutputStream.writeInt(attribute);
		dataOutputStream.writeLong(value);
	}

	public void populateFrom(byte[] inputBuffer, int startingOffset) throws IOException {
		attribute = Utils.readBigEndInt(inputBuffer, startingOffset);
		value = Utils.readBigEndLong(inputBuffer, startingOffset+4);
	}

	protected void prepareResultOrExecute(I802_15_4_MAC mac) {
		mac.mlmeSet(attribute, value);
	}
	
	protected byte classIndicatorByte() {
		return MLMESetCommand;
	}
}
