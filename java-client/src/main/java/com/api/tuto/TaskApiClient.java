package com.api.tuto;

import io.swagger.client.ApiException;
import io.swagger.client.api.TaskresourceApi;
import io.swagger.client.model.Task;

public class TaskApiClient {
    public static void main(String[] args) throws ApiException {
        TaskresourceApi api = new TaskresourceApi();
        for (Task task : api.getTasksUsingGET()) {
            System.out.println(task);
        }
    }
}