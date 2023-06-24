package com.learn.SpringHibernate.Beans;

public class UpdatesAnalytics {
		String name;
		Integer updates;
		Integer rownum_;
		
		@Override
		public String toString() {
			return "UpdatesAnalytics [name=" + name + ", updates=" + updates + "]";
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getUpdates() {
			return updates;
		}

		public void setUpdates(Integer updates) {
			this.updates = updates;
		}

		public Integer getRownum_() {
			return rownum_;
		}

		public void setRownum_(Integer rownum_) {
			this.rownum_ = rownum_;
		}
		
		
		
		
}
