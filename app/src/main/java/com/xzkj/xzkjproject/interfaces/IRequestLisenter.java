package com.xzkj.xzkjproject.interfaces;

import com.xzkj.xzkjproject.model.RequestDto;

public interface IRequestLisenter<T> {
    void onSuccessData(RequestDto dto);

    void onErrer(int code, String msg);
}
