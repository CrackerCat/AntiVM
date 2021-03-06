/*
 * MIT License   Project AntiVM
 *
 * Copyright (c) 2017 Bunny Blue
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package io.github.antivm.impl;

import android.content.Context;

import java.io.File;

import io.github.antivm.IAntiVM;
import io.github.vmBoy.Who;

/**
 * detect uninstall app,this app should not exist in /data/app
 * and this apk owner should be system.
 * so we try delete pkg.apk use delete,if failure,try unlink,
 * then detect owner of apk
 * Created by bunnyblue on 4/22/17.
 */

public class AntiVMUninstalled extends IAntiVM {
    @Override
    public boolean antiVM(Context context) {
        String pkgPath = context.getPackageCodePath();
        File file = new File(pkgPath);
        if (file.delete()) {
            return true;
        }
        if (Who.unlink(pkgPath) == 0) {//删除成功

            return true;
        }
        if (Who.permission(pkgPath) == -1) {
            return true;
        }
        return false;
    }
}
