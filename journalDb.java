import java.util.ArrayList;
import java.util.List;

public class journalDb {
	private static final String TABLE_NAME = "Journal Info";
	private static final String USERNAME_COLUMN = "Username";
	private static final String MONTH_COLUMN = "Month";
	private static final String DATE_COLUMN = "Date";
	private static final String YEAR_COLUMN = "Year";
	private static final String X_VALUE_COLUMN = "X-Coordinate";
	private static final String Y_VALUE_COLUMN = "Y-Coordinate";
	private static final String LENGTH_COLUMN = "Length";
	private static final String PRIVACY_COLUMN = "Privacy Setting"; 
	private static final String SPECIES_COLUMN = "Species";
	private static final String IMAGE_COLUMN = "Image";
	private static final String BAIT_COLUMN = "Bait";
	private static final String WIND_VALUE_COLUMN = "Wind";
	private static final String TIDE_VALUE_COLUMN = "Tide";
	private static final String NOTES_COLUMN = "Notes";
	static String JOURNAL_SELECTED; 
			
	private static final String TABLE_CREATE =
			"CREATE TABLE " + TABLE_NAME + "(" + USERNAME_COLUMN + " TEXT PRIMARY KEY NOT NULL," 
			+ MONTH_COLUMN + " TEXT," + DATE_COLUMN + " TEXT," + YEAR_COLUMN + " TEXT," 
			+ X_VALUE_COLUMN + " REAL," + Y_VALUE_COLUMN + " REAL," + LENGTH_COLUMN + " REAL," 
			+ PRIVACY_COLUMN + " INT," + SPECIES_COLUMN + " TEXT," + BAIT_COLUMN + " TEXT," + 
			WIND_VALUE_COLUMN + " TEXT," +  TIDE_VALUE_COLUMN + " Text," + NOTES_COLUMN + " TEXT)";
	
	private static final String DELETE_ENTRIES = "DROP TABLE IF EXISTS" + TABLE_NAME;
	
	public void insertJournalEntry() {
		
		SQLiteDatabase db = db.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(USERNAME_COLUMN, CreateEntry.loggedinuser);
		values.put(MONTH_COLUMN, CreateEntry.monthValue);
		values.put(DATE_COLUMN, CreateEntry.dateValue);
		values.put(YEAR_COLUMN, CreateEntry.yearValue);
		values.put(X_VALUE_COLUMN, CreateEntry.xcoValue);
		values.put(Y_VALUE_COLUMN, CreateEntry.ycoValue);
		values.put(LENGTH_COLUMN, CreateEntry.lengthValue);
		values.put(PRIVACY_COLUMN, CreateEntry.privacy);
		values.put(SPECIES_COLUMN, CreateEntry.speciesValue);
		values.put(IMAGE_COLUMN, CreateEntry.myImage);
		values.put(BAIT_COLUMN, CreateEntry.baitValue);
		values.put(WIND_VALUE_COLUMN, CreateEntry.windValue);
		values.put(TIDE_VALUE_COLUMN, CreateEntry.tideValue);
		values.put(NOTES_COLUMN, CreateEntry.narrativeValue);
		
		long newRowID = db.insert(TABLE_NAME, null, values);
	}
	
	public void retrieveJournalEntry() {
		db = db.getReadableDatabase();
		
		String[] projection = {
				USERNAME_COLUMN,
				MONTH_COLUMN,
				DATE_COLUMN,
				YEAR_COLUMN,
				X_VALUE_COLUMN,
				Y_VALUE_COLUMN,
				LENGTH_COLUMN,
				PRIVACY_COLUMN,
				SPECIES_COLUMN,
				IMAGE_COLUMN,
				BAIT_COLUMN,
				WIND_VALUE_COLUMN,
				TIDE_VALUE_COLUMN,
				NOTES_COLUMN
		};

		String selection = searchCriteria + " =?";
		String[] selectionArgs = {}; 
		
		String sortOrder = MONTH_COLUMN + " DESC";
		
		Cursor cursor = db.query(
				TABLE_NAME,
				projection,
				selection,
				selectionArgs,
				null,
				null,
				sortOrder
				);
		
		List itemIds = new ArrayList<>();
		while(cursor.moveToNext()) {
		  long itemId = cursor.getLong(
		      cursor.getColumnIndexOrThrow(loggedinuser));
		  itemIds.add(itemId);
		}
		cursor.close();
	}
	
	public void deleteJournal() {
		String selection = JOURNAL_SELECTED + " LIKE ?";
		String [] selectionArgs = {};
		
		int deleted = db.delete(TABLE_NAME, selection, selectionArgs);
	}
	
	public void editJournalEntry() {
		db = db.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(JOURNAL_SELECTED + " LIKE ?");
		
		String selection = JOURNAL_SELECTED + " LIKE ?";
		String selectionArgs = {};
		
		int count = db.update(TABLE_NAME, values, selection, selectionArgs);
	}
}
