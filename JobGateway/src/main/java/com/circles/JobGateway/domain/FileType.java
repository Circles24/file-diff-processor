package com.circles.JobGateway.domain;

public enum FileType {
    BUYERS_SHEET {
        @Override
        public void setUploadFilePath(Job job, String filePath) {
            job.setBuyersFilePath(filePath);
        }
    },
    SUPPLIERS_SHEET {
        @Override
        public void setUploadFilePath(Job job, String filePath) {
            job.setSuppliersFilePath(filePath);
        }
    };

    public abstract void setUploadFilePath(Job job, String filePath);
}
