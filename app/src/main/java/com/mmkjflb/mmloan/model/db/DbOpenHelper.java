package com.mmkjflb.mmloan.model.db;

import javax.inject.Singleton;

/**
 * Created by Administrator on 2017/9/29.
 */

@Singleton
public class DbOpenHelper  {

//		extends DaoMaster.OpenHelper {
//
//	@Inject
//	public DbOpenHelper(@ApplicationContext Context context, @DatabaseInfo String name) {
//		super(context, name);
//	}
//
//	@Override
//	public void onUpgrade(Database db, int oldVersion, int newVersion) {
//		super.onUpgrade(db, oldVersion, newVersion);
//		KLog.d("DEBUG", "DB_OLD_VERSION : " + oldVersion + ", DB_NEW_VERSION : " + newVersion);
//		switch (oldVersion) {
//			case 1:
//			case 2:
//				//db.execSQL("ALTER TABLE " + UserDao.TABLENAME + " ADD COLUMN "
//				// + UserDao.Properties.Name.columnName + " TEXT DEFAULT 'DEFAULT_VAL'");
//		}
//	}
}