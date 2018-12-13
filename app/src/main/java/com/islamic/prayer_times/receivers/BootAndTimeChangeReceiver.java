/*
 *  Copyright © 2017 Djalel Chefrour
 *
 *  This file is part of Bilal.
 *
 *  Bilal is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Bilal is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Bilal.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.islamic.prayer_times.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import timber.log.Timber;

import com.islamic.prayer_times.PrayerTimesManager;
import com.islamic.prayer_times.activities.MainActivity;

public class BootAndTimeChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Timber.i("=============== " + action);

        if (null != action) {
            if (action.equals(Intent.ACTION_BOOT_COMPLETED))
            {
                PrayerTimesManager.handleBootComplete(context);
            }
            else // TIME_SET
            {
                PrayerTimesManager.handleTimeChange(context);

                // Broadcast to MainActivity so it updates its screen if on
                Intent updateIntent = new Intent(MainActivity.UPDATE_VIEWS);
                context.sendBroadcast(updateIntent);
            }
        }
    }
}
