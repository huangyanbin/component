package com.palmwifi.utils;

import android.Manifest;

import com.orhanobut.logger.Logger;
import com.palmwifi.mvp.IView;
import com.tbruyelle.rxpermissions.RxPermissions;


import rx.functions.Action1;


/**
 * Created by jess on 17/10/2016 10:09
 * Contact with jess.yan.effort@gmail.com
 */

public class PermissionUtils {

    public interface RequestPermission {
        void onRequestPermissionSuccess();
        void onRequestPermissionFailure();
    }


    /**
     * 请求摄像头权限
     */
    public static void launchCamera(final RequestPermission requestPermission,IView iView) {
        //先确保是否已经申请过摄像头，和写入外部存储的权限
        RxPermissions rxPermissions = new RxPermissions(BaseUtils.getActivity(iView)); // where this is an Activity instance
        boolean isPermissionsGranted =
                rxPermissions.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE) &&
                        rxPermissions.isGranted(Manifest.permission.CAMERA);

        if (isPermissionsGranted) {//已经申请过，直接执行操作
            requestPermission.onRequestPermissionSuccess();
        } else {//没有申请过，则申请
            rxPermissions
                    .request(Manifest.permission.WRITE_EXTERNAL_STORAGE
                            , Manifest.permission.CAMERA)
                    .subscribe(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean granted) {
                            if (granted) { // Always true pre-M
                                requestPermission.onRequestPermissionSuccess();
                                Logger.d("request Camera Permission suc");
                            } else {
                                requestPermission.onRequestPermissionFailure();
                                Logger.e("request Camera Permission failure");
                            }

                        }
                    });

        }
    }


    /**
     * 请求外部存储的权限
     */
    public static void externalStorage(final RequestPermission requestPermission,IView iView) {
        //先确保是否已经申请过摄像头，和写入外部存储的权限
        RxPermissions rxPermissions = new RxPermissions(BaseUtils.getActivity(iView)); // where this is an Activity instance
        boolean isPermissionsGranted =
                rxPermissions
                        .isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (isPermissionsGranted) {//已经申请过，直接执行操作
            requestPermission.onRequestPermissionSuccess();
        } else {//没有申请过，则申请
            rxPermissions
                    .request(Manifest.permission.WRITE_EXTERNAL_STORAGE
                            , Manifest.permission.CAMERA)
                    .subscribe(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean granted) {
                            if (granted) { // Always true pre-M
                                requestPermission.onRequestPermissionSuccess();
                                Logger.d("request externalStorage Permission suc");
                            } else {
                                requestPermission.onRequestPermissionFailure();
                                Logger.e("request externalStorage Permission failure");
                            }

                        }
                    });
        }
    }


    /**
     * 请求发送短信权限
     */
    public static void sendSms(final RequestPermission requestPermission,final IView view) {
        RxPermissions rxPermissions = new RxPermissions(BaseUtils.getActivity(view)); // where this is an Activity instance
//先确保是否已经申请过权限
        boolean isPermissionsGranted =
                rxPermissions
                        .isGranted(Manifest.permission.SEND_SMS);

        if (isPermissionsGranted) {//已经申请过，直接执行操作
            requestPermission.onRequestPermissionSuccess();
        } else {//没有申请过，则申请
            rxPermissions
                    .request(Manifest.permission.SEND_SMS)
                    .subscribe(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean granted) {
                            if (granted) { // Always true pre-M
                                requestPermission.onRequestPermissionSuccess();
                                Logger.d("request sendSms Permission suc");
                            } else {
                                requestPermission.onRequestPermissionFailure();
                                Logger.e("request sendSms Permission failure");
                            }

                        }
                    });
        }
    }


    /**
     * 请求打电话权限
     */
    public static void callPhone(final RequestPermission requestPermission,final IView view) {
//先确保是否已经申请过权限
        RxPermissions rxPermissions = new RxPermissions(BaseUtils.getActivity(view)); // where this is an Activity instance
        boolean isPermissionsGranted =
                rxPermissions
                        .isGranted(Manifest.permission.CALL_PHONE);

        if (isPermissionsGranted) {//已经申请过，直接执行操作
            requestPermission.onRequestPermissionSuccess();
        } else {//没有申请过，则申请
            rxPermissions
                    .request(Manifest.permission.CALL_PHONE)
                    .subscribe(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean granted) {
                            if (granted) { // Always true pre-M
                                requestPermission.onRequestPermissionSuccess();
                                Logger.d("request callPhone Permission suc");
                            } else {
                                requestPermission.onRequestPermissionFailure();
                                Logger.e("request callPhone Permission failure");
                            }

                        }
                    });
        }
    }


    /**
     * 请求获取手机状态的权限
     */
    public static void readPhonestate(final RequestPermission requestPermission,final IView view) {
//先确保是否已经申请过权限
        RxPermissions rxPermissions = new RxPermissions(BaseUtils.getActivity(view)); // where this is an Activity instance
        boolean isPermissionsGranted =
                rxPermissions
                        .isGranted(Manifest.permission.READ_PHONE_STATE);

        if (isPermissionsGranted) {//已经申请过，直接执行操作
            requestPermission.onRequestPermissionSuccess();
        } else {//没有申请过，则申请
            rxPermissions
                    .request(Manifest.permission.READ_PHONE_STATE)
                    .subscribe(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean granted) {
                            if (granted) { // Always true pre-M
                                requestPermission.onRequestPermissionSuccess();
                                Logger.d("request readPhoneState Permission suc");
                            } else {
                                requestPermission.onRequestPermissionFailure();
                                Logger.e("request readPhoneState Permission failure");
                            }

                        }
                    });
        }
    }

}

