package com.opentable.assignment.dao;

import pojo.Image;

import java.util.List;

public interface ImageDao {
    void save(Image image);

    List<Image> getAll();
}
