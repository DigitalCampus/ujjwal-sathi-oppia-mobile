/* 
 * This file is part of OppiaMobile - https://digital-campus.org/
 * 
 * OppiaMobile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * OppiaMobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with OppiaMobile. If not, see <http://www.gnu.org/licenses/>.
 */

package org.digitalcampus.oppia.task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.ujjwal.saathi.oppia.mobile.learning.R;
import org.digitalcampus.oppia.activity.PrefsActivity;
import org.digitalcampus.oppia.application.DatabaseManager;
import org.digitalcampus.oppia.application.DbHelper;
import org.digitalcampus.oppia.application.MobileLearning;
import org.digitalcampus.oppia.exception.InvalidXMLException;
import org.digitalcampus.oppia.listener.UpgradeListener;
import org.digitalcampus.oppia.model.Activity;
import org.digitalcampus.oppia.model.Course;
import org.digitalcampus.oppia.model.QuizAttempt;
import org.digitalcampus.oppia.model.User;
import org.digitalcampus.oppia.utils.SearchUtils;
import org.digitalcampus.oppia.utils.storage.FileUtils;
import org.digitalcampus.oppia.utils.xmlreaders.CourseScheduleXMLReader;
import org.digitalcampus.oppia.utils.xmlreaders.CourseTrackerXMLReader;
import org.digitalcampus.oppia.utils.xmlreaders.CourseXMLReader;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class UpgradeManagerTask extends AsyncTask<Payload, String, Payload> {
	
	public static final String TAG = UpgradeManagerTask.class.getSimpleName();
	private Context ctx;
	private SharedPreferences prefs;
	private UpgradeListener mUpgradeListener;
	
	private static final String PREF_API_KEY = "prefApiKey";
	private static final String PREF_BADGES = "prefBadges";
	private static final String PREF_POINTS = "prefPoints";
	
	public UpgradeManagerTask(Context ctx){
		this.ctx = ctx;
		prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
	}
	
	@Override
	protected Payload doInBackground(Payload... params) {
		Payload payload = params[0];
		
		payload.setResult(false);
		if(!prefs.getBoolean("upgradeV15",false)){
			Editor editor = prefs.edit();
			editor.putBoolean("upgradeV15", true);
			editor.commit();
			publishProgress(this.ctx.getString(R.string.info_upgrading,"v15"));
			payload.setResult(true);
		}
		if(!prefs.getBoolean("upgradeV17",false)){
			upgradeV17();
			Editor editor = prefs.edit();
			editor.putBoolean("upgradeV17", true);
			editor.commit();
			publishProgress(this.ctx.getString(R.string.info_upgrading,"v17"));
			payload.setResult(true);
		}
		
		if(!prefs.getBoolean("upgradeV20",false)){
			upgradeV20();
			Editor editor = prefs.edit();
			editor.putBoolean("upgradeV20", true);
			editor.commit();
			publishProgress(this.ctx.getString(R.string.info_upgrading,"v20"));
			payload.setResult(true);
		}
		
		if(!prefs.getBoolean("upgradeV29",false)){
			Editor editor = prefs.edit();
			editor.putBoolean("upgradeV29", true);
			editor.commit();
			publishProgress(this.ctx.getString(R.string.info_upgrading,"v29"));
			payload.setResult(true);
		}
		
		if(!prefs.getBoolean("upgradeV43",false)){
			upgradeV43();
			Editor editor = prefs.edit();
			editor.putBoolean("upgradeV43", true);
			editor.commit();
			publishProgress(this.ctx.getString(R.string.info_upgrading,"v43"));
			payload.setResult(true);
		}
		if(!prefs.getBoolean("upgradeV44",false)){
			Editor editor = prefs.edit();
			editor.putBoolean("upgradeV44", true);
			editor.commit();
			publishProgress(this.ctx.getString(R.string.info_upgrading,"v44"));
			payload.setResult(true);
		}
		
		if(!prefs.getBoolean("upgradeV45",false)){
			Editor editor = prefs.edit();
			editor.putBoolean("upgradeV45", true);
			editor.commit();
			publishProgress(this.ctx.getString(R.string.info_upgrading,"v45"));
			payload.setResult(true);
		}
		
		if(!prefs.getBoolean("upgradeV49b",false)){
			Editor editor = prefs.edit();
			editor.putBoolean("upgradeV49b", true);
			editor.commit();
			publishProgress(this.ctx.getString(R.string.info_upgrading,"v49b"));
			payload.setResult(true);
		}
		
		if(!prefs.getBoolean("upgradeV46",false)){
			Editor editor = prefs.edit();
			editor.putBoolean("upgradeV46", true);
			//editor.putString("prefServer", ctx.getString(R.string.prefServerDefault));
			editor.commit();
			publishProgress(this.ctx.getString(R.string.info_upgrading,"v46"));
			payload.setResult(true);
		}
		
		if(!prefs.getBoolean("upgradeV49c",false)){
			upgradeV49();
			Editor editor = prefs.edit();
			editor.putBoolean("upgradeV49c", true);
			editor.commit();
			publishProgress(this.ctx.getString(R.string.info_upgrading,"v49c"));
			payload.setResult(true);
		}
		
		if(!prefs.getBoolean("upgradeV55a",false)){
			Editor editor = prefs.edit();
			editor.putBoolean("upgradeV55a", true);
			editor.commit();
			publishProgress(this.ctx.getString(R.string.info_upgrading,"v55a"));
			payload.setResult(true);
		}
		
		if(!prefs.getBoolean("upgradeV55b",false)){
			upgradeV55b();
			Editor editor = prefs.edit();
			editor.putBoolean("upgradeV55b", true);
			editor.commit();
			publishProgress(this.ctx.getString(R.string.info_upgrading,"v55b"));
			payload.setResult(true);
		}
		
		if(!prefs.getBoolean("upgradeV56a",false)){
			upgradeV56a();
			Editor editor = prefs.edit();
			editor.putBoolean("upgradeV56a", true);
			editor.commit();
			publishProgress(this.ctx.getString(R.string.info_upgrading,"v56a"));
			payload.setResult(true);
		}
		if(!prefs.getBoolean("upgradeV56b",false)){
			Editor editor = prefs.edit();
			editor.putBoolean("upgradeV56b", true);
			editor.commit();
			publishProgress(this.ctx.getString(R.string.info_upgrading,"v56b"));
			payload.setResult(true);
		}
		return payload;
	}
	
	/* rescans all the installed courses and reinstalls them, to ensure that 
	 * the new titles etc are picked up
	 */
	protected void upgradeV17(){
		File dir = new File(FileUtils.getCoursesPath(ctx));
		String[] children = dir.list();
		if (children != null) {
			for (int i = 0; i < children.length; i++) {
				publishProgress("checking: " + children[i]);
				String courseXMLPath = "";
				String courseScheduleXMLPath = "";
				String courseTrackerXMLPath = "";
				// check that it's unzipped etc correctly
				try {
					courseXMLPath = dir + File.separator + children[i] + File.separator + MobileLearning.COURSE_XML;
					courseScheduleXMLPath = dir + File.separator + children[i] + File.separator + MobileLearning.COURSE_SCHEDULE_XML;
					courseTrackerXMLPath = dir + File.separator + children[i] + File.separator + MobileLearning.COURSE_TRACKER_XML;
				} catch (ArrayIndexOutOfBoundsException aioobe){
					FileUtils.cleanUp(dir, FileUtils.getDownloadPath(ctx) + children[i]);
					break;
				}
				
				// check a module.xml file exists and is a readable XML file
				CourseXMLReader cxr;
				CourseScheduleXMLReader csxr;
				CourseTrackerXMLReader ctxr;
				try {
					cxr = new CourseXMLReader(courseXMLPath, 0, ctx);
					csxr = new CourseScheduleXMLReader(courseScheduleXMLPath);
					File trackerXML = new File(courseTrackerXMLPath);
					ctxr = new CourseTrackerXMLReader(trackerXML);
				} catch (InvalidXMLException e) {
					e.printStackTrace();
					break;
				}

				Course c = new Course(prefs.getString(PrefsActivity.PREF_STORAGE_LOCATION, ""));
				c.setVersionId(cxr.getVersionId());
				c.setTitles(cxr.getTitles());
				c.setShortname(children[i]);
				c.setImageFile(children[i] + File.separator + cxr.getCourseImage());
				c.setLangs(cxr.getLangs());
				c.setPriority(cxr.getPriority());
				
				DbHelper db = new DbHelper(ctx);
				long courseId = db.addOrUpdateCourse(c);
				
				if (courseId != -1) {
					db.insertActivities(cxr.getActivities(courseId));
					db.insertTrackers(ctxr.getTrackers(courseId, 0));
				} 
				
				// add schedule
				// put this here so even if the course content isn't updated the schedule will be
				db.insertSchedule(csxr.getSchedule());
				db.updateScheduleVersion(courseId, csxr.getScheduleVersion());
				
				DatabaseManager.getInstance().closeDatabase();
			}
		}
	}
	
	/* switch to using demo.oppia-mobile.org
	 */
	protected void upgradeV20(){
		Editor editor = prefs.edit();
		editor.putString(PrefsActivity.PREF_SERVER, ctx.getString(R.string.prefServerDefault));
		editor.commit();
	}
	
	/* go through and add html content to tables
	 */
	protected void upgradeV43(){
		SearchUtils.reindexAll(ctx);
		prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
		User user = new User();
		user.setUsername(prefs.getString(PrefsActivity.PREF_USER_NAME, ""));
		user.setApiKey(prefs.getString(UpgradeManagerTask.PREF_API_KEY, "") );
		DbHelper db = new DbHelper(ctx);
		long userId = db.addOrUpdateUser(user);
		db.updateV43(userId);
		DatabaseManager.getInstance().closeDatabase();
		
	}
	
	/*
	 * Move files from current location into new one
	 */
	protected void upgradeV49(){
		
		String location = prefs.getString(PrefsActivity.PREF_STORAGE_LOCATION, "");
		if ((location == null) || !location.equals("")){ return; }
		
		String source = Environment.getExternalStorageDirectory() + File.separator + FileUtils.APP_ROOT_DIR_NAME  + File.separator;
    	
    	File[] dirs = ContextCompat.getExternalFilesDirs(ctx,null);
    	if(dirs.length > 0){

	    	String destination = dirs[0].getAbsolutePath();
	    	File downloadSource = new File(source + FileUtils.APP_DOWNLOAD_DIR_NAME);
			File mediaSource = new File(source +  FileUtils.APP_MEDIA_DIR_NAME);
			File courseSource = new File(source +  FileUtils.APP_COURSES_DIR_NAME);

            publishProgress(this.ctx.getString(R.string.upgradev49_1,""));
	    	try {
				org.apache.commons.io.FileUtils.forceDelete(new File (destination + File.separator + FileUtils.APP_DOWNLOAD_DIR_NAME ));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.d(TAG,"failed to delete: " + destination + File.separator + FileUtils.APP_DOWNLOAD_DIR_NAME );
				e.printStackTrace();
            }
			
			try {
				org.apache.commons.io.FileUtils.forceDelete(new File (destination + File.separator + FileUtils.APP_MEDIA_DIR_NAME ));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.d(TAG,"failed to delete: " + destination + File.separator + FileUtils.APP_MEDIA_DIR_NAME );
				e.printStackTrace();
            }
			
			try {
				org.apache.commons.io.FileUtils.forceDelete(new File (destination + File.separator + FileUtils.APP_COURSES_DIR_NAME ));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.d(TAG,"failed to delete: " + destination + File.separator + FileUtils.APP_COURSES_DIR_NAME );
				e.printStackTrace();
            }

			// now copy over 
			try {
				
				org.apache.commons.io.FileUtils.moveDirectoryToDirectory(downloadSource,new File(destination),true);
				Log.d(TAG,"completed");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.d(TAG,"failed");
				e.printStackTrace();
            }

			try {
				org.apache.commons.io.FileUtils.moveDirectoryToDirectory(mediaSource,new File(destination),true);
				Log.d(TAG,"completed");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.d(TAG,"failed");
				e.printStackTrace();
            }
			
			try {
				org.apache.commons.io.FileUtils.moveDirectoryToDirectory(courseSource,new File(destination),true);
				Log.d(TAG,"completed");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.d(TAG,"failed");
				e.printStackTrace();
            }
			
			Editor editor = prefs.edit();
			editor.putString(PrefsActivity.PREF_STORAGE_LOCATION, destination);
			editor.commit();
			
			// delete original dir
			try {
				org.apache.commons.io.FileUtils.forceDelete(new File(source));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.d(TAG,"failed to delete original file");
			}
    	}
	}
	
	// update all the current quiz results for the score/maxscore etc
	protected void upgradeV55b(){
		DbHelper db = new DbHelper(ctx);
		ArrayList<QuizAttempt> quizAttempts = db.getAllQuizAttempts();
		long userId = db.getUserId(prefs.getString(PrefsActivity.PREF_USER_NAME, ""));
		
		ArrayList<Course> courses = db.getAllCourses();
		ArrayList<v54UpgradeQuizObj> quizzes = new ArrayList<v54UpgradeQuizObj>();
		
		for (Course c: courses){
			try {
				CourseXMLReader cxr = new CourseXMLReader(c.getCourseXMLLocation(),c.getCourseId(),ctx);
				
				ArrayList<Activity> baseActs = cxr.getBaselineActivities();
				for (Activity a: baseActs){
					if (a.getActType().equalsIgnoreCase("quiz")){
						String quizContent = a.getContents("en");
						try {
							JSONObject quizJson = new JSONObject(quizContent);
							v54UpgradeQuizObj q = new v54UpgradeQuizObj();
							q.id = quizJson.getInt("id");
							q.digest = quizJson.getJSONObject("props").getString("digest");
							q.threshold = quizJson.getJSONObject("props").getInt("passthreshold");
							quizzes.add(q);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
				// now add the standard activities
				ArrayList<Activity> acts = cxr.getActivities(c.getCourseId());
				for (Activity a: acts){
					if (a.getActType().equalsIgnoreCase("quiz")){
						String quizContent = a.getContents("en");
						try {
							JSONObject quizJson = new JSONObject(quizContent);
							v54UpgradeQuizObj q = new v54UpgradeQuizObj();
							q.id = quizJson.getInt("id");
							q.digest = quizJson.getJSONObject("props").getString("digest");
							q.threshold = quizJson.getJSONObject("props").getInt("passthreshold");
							quizzes.add(q);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			} catch (InvalidXMLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		
		for (QuizAttempt qa: quizAttempts){
			// data back to json obj
			try {
				JSONObject jsonData = new JSONObject(qa.getData());
				qa.setMaxscore((float) jsonData.getDouble("maxscore"));
				qa.setScore((float) jsonData.getDouble("score"));
				
				int quizId = jsonData.getInt("quiz_id");
				
				v54UpgradeQuizObj currentQuiz = null;
				
				// find the relevant quiz in quizzes
				for (v54UpgradeQuizObj tmpQuiz: quizzes){
					if (tmpQuiz.id == quizId){
						currentQuiz = tmpQuiz;
						break;
					}
				}
				
				if (currentQuiz == null){
					Log.d(TAG,"not found");
				} else {
					Log.d(TAG,"Found");
					qa.setActivityDigest(currentQuiz.digest);
					if(qa.getScoreAsPercent() >= currentQuiz.threshold){
						qa.setPassed(true);
					} else {
						qa.setPassed(false);
					}
				}
				
				// make the actual updates
				qa.setUserId(userId);
				db.updateQuizAttempt(qa);
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		ArrayList<QuizAttempt> checkQuizAttempts = db.getAllQuizAttempts();
		for (QuizAttempt qa: checkQuizAttempts){
			// display current data
			Log.d(TAG, "data: " + qa.getData());
			Log.d(TAG, "digest: " + qa.getActivityDigest());
			Log.d(TAG, "userid: " + qa.getUserId());
			Log.d(TAG, "courseid: " + qa.getCourseId());
			Log.d(TAG, "score: " + qa.getScore());
			Log.d(TAG, "maxscore: " + qa.getMaxscore());
			Log.d(TAG, "passed: " + qa.isPassed());
		}
		
		DatabaseManager.getInstance().closeDatabase();
		
	}
	
	private class v54UpgradeQuizObj{
		public int id;
		public String digest;
		public int threshold;
	}
	
	protected void upgradeV56a(){
		DbHelper db = new DbHelper(ctx);
		long userId = db.getUserId(prefs.getString(PrefsActivity.PREF_USER_NAME, ""));
		int points = prefs.getInt(UpgradeManagerTask.PREF_POINTS, 0);
		int badges = prefs.getInt(UpgradeManagerTask.PREF_BADGES, 0);
		Log.d(TAG,"points: " + points);
		db.updateUserPoints(userId, points);
		db.updateUserBadges(userId, badges);
		DatabaseManager.getInstance().closeDatabase();
	}
	
	@Override
	protected void onProgressUpdate(String... obj) {
		synchronized (this) {
            if (mUpgradeListener != null) {
                // update progress and total
            	mUpgradeListener.upgradeProgressUpdate(obj[0]);
            }
        }
	}

	@Override
	protected void onPostExecute(Payload p) {
		synchronized (this) {
            if (mUpgradeListener != null) {
            	mUpgradeListener.upgradeComplete(p);
            }
        }
	}

	public void setUpgradeListener(UpgradeListener srl) {
        synchronized (this) {
        	mUpgradeListener = srl;
        }
    }


}
