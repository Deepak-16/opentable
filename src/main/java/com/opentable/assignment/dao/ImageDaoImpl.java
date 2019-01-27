package com.opentable.assignment.dao;

import com.opentable.assignment.dao.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pojo.Image;

import java.util.List;

@Repository
public class ImageDaoImpl implements ImageDao{

    @Autowired
    ImageRepository imageRepository;

    @Override
    public void save(Image image){
        imageRepository.save(image);
    }

    @Override
    public List<Image> getAll(){
        return imageRepository.findAll();
    }
}
