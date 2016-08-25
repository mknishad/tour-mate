package com.example.nishad.tourmate.model;

import java.util.Arrays;

/**
 * Created by Nishad on 8/25/2016.
 */
public class PhotoMoment {

    // private variables
    int _id;
    String _name;
    byte[] _image;
    int _userIdForeign;

    // Empty constructor
    public PhotoMoment() {

    }

    // constructor
    public PhotoMoment(int keyId, String name, byte[] image, int userIdForeign) {
        this._id = keyId;
        this._name = name;
        this._image = image;
        this._userIdForeign = userIdForeign;
    }

    public PhotoMoment(String name, byte[] image, int userIdForeign) {
        this._name = name;
        this._image = image;
        this._userIdForeign = userIdForeign;
    }
    public PhotoMoment(int keyId) {
        this._id = keyId;

    }

    // getting ID
    public int getID() {
        return this._id;
    }

    // setting id
    public void setID(int keyId) {
        this._id = keyId;
    }

    // getting name
    public String getName() {
        return this._name;
    }

    // setting name
    public void setName(String name) {
        this._name = name;
    }

    // getting phone number
    public byte[] getImage() {
        return this._image;
    }

    // setting phone number
    public void setImage(byte[] image) {
        this._image = image;
    }

    public int get_userIdForeign() {
        return _userIdForeign;
    }

    public void set_userIdForeign(int _userIdForeign) {
        this._userIdForeign = _userIdForeign;
    }

    @Override
    public String toString() {
        return "PhotoMoment{" +
                "_id=" + _id +
                ", _name='" + _name + '\'' +
                ", _image=" + Arrays.toString(_image) +
                ", _userIdForeign=" + _userIdForeign +
                '}';
    }
}