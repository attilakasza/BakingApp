package com.attilakasza.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.attilakasza.bakingapp.R;
import com.attilakasza.bakingapp.activities.MainActivity;

/**
 * Implementation of App Widget functionality.
 */
public class IngredientWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredient_widget_provider);

            Intent ingIntent = new Intent(context, WidgetService.class);
            views.setRemoteAdapter(R.id.widget_list, ingIntent);

            Intent appIntent = new Intent(context, MainActivity.class);
            PendingIntent startAppIntent = PendingIntent.getActivity(context, 0, appIntent, 0);
            views.setPendingIntentTemplate(R.id.widget_list, startAppIntent);

            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_list);
            appWidgetManager.updateAppWidget(appWidgetIds, views);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {
            ComponentName name = new ComponentName(context, IngredientWidgetProvider.class);
            AppWidgetManager manager = AppWidgetManager.getInstance(context);
            manager.notifyAppWidgetViewDataChanged(manager.getAppWidgetIds(name), R.id.widget_list);
        }
        super.onReceive(context, intent);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}