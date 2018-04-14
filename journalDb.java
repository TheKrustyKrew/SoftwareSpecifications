import java.util.ArrayList;
import java.util.List;

public class journalDb {
	private static final String TABLE_NAME = "Journal Info";
	private static final String USERNAME_COLUMN = "Username";
	private static final String MONTH_COLUMN = "Month";
	private static final String DATE_COLUMN = "Date";
	private static final String YEAR_COLUMN = "Year";
	private static final String SPECIES_COLUMN = "Species";
	private static final String BAIT_COLUMN = "Bait";
	private static final String WIND_VALUE_COLUMN = "Wind";
	private static final String TIDE_VALUE_COLUMN = "Tide";
	private static final String NOTES_COLUMN = "Notes";
			
	private static final String TABLE_CREATE =
			"CREATE TABLE" + TABLE_NAME + "(" + USERNAME_COLUMN
			+ " TEXT PRIMARY KEY," + MONTH_COLUMN + " TEXT," + DATE_COLUMN + " TEXT," +
			YEAR_COLUMN + " TEXT," + SPECIES_COLUMN + " TEXT," + BAIT_COLUMN + " TEXT," + 
			WIND_VALUE_COLUMN + " TEXT," + NOTES_COLUMN + " TEXT)";
	
	private static final String DELETE_ENTRIES = "DROP TABLE IF EXISTS" + TABLE_NAME;
	
	public void insertJournalEntry() {
		
		SQLiteDatabase db = db.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(MONTH_COLUMN, month);
		values.put(YEAR_COLUMN, year);
		values.put(SPECIES_COLUMN, species);
		values.put(NOTES_COLUMN, text);
		
		long newRowID = db.insert(TABLE_NAME, null, values);
				
		List itemIds = new ArrayList<>();
		while(cursor.moveToNext()) {
			long itemId = cursor.getLong(
					cursor.getColumnIndexOrThrow(USERNAME));
			itemIds.add(itemId);
		}
		
		public void retrieveJournalEntry() {
			db = db.getReadableDatabase();
			
			String[] projection = {
					USERNAME,
					MONTH_COLUMN,
					YEAR_COLUMN,
					SPECIES_COLUMN,
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
		}
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
