package com.qa.cbol.utils;

	public enum Status {

		PASS("PASS"), FAIL("FAIL"), SKIP("SKIP"), INFO("INFO"), ERROR("ERROR"), WARNING("WARNING"), DBPASS(
				"DBPASS"), DBFAIL("DBFAIL");

		private String logType;

		Status(String logType) {
			this.logType = logType;
		}

		public String getStatus() {
			return logType;
		}

		@Override
		public String toString() {
			return logType;
		}

	}

