package model;

import model.exceptions.NonExistentTeachRequestException;

public class PTTDirector extends Employee {

    private TeachRequestMap teachRequestMap;

    public PTTDirector(String name, TeachRequestMap teachRequestMap) {
        super(name);
        this.teachRequestMap = teachRequestMap;
    }

    public void approveTeachRequest(Course course) {
        try {
            teachRequestMap.approveTeachRequest(course);
        } catch (NonExistentTeachRequestException e) {
            e.printStackTrace();
        }
    }
}
