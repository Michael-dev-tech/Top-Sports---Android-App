package com.example.topsports.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.topsports.data.local.entity.Athlete;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AthleteDao_Impl implements AthleteDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Athlete> __insertionAdapterOfAthlete;

  private final EntityDeletionOrUpdateAdapter<Athlete> __deletionAdapterOfAthlete;

  private final EntityDeletionOrUpdateAdapter<Athlete> __updateAdapterOfAthlete;

  public AthleteDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAthlete = new EntityInsertionAdapter<Athlete>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `athletes` (`id`,`username`,`full_name`,`age`,`city`,`sport`,`years_experience`,`bio`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Athlete entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getUsername() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getUsername());
        }
        if (entity.getFullName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getFullName());
        }
        statement.bindLong(4, entity.getAge());
        if (entity.getCity() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCity());
        }
        if (entity.getSport() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getSport());
        }
        statement.bindLong(7, entity.getYearsExperience());
        if (entity.getBio() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getBio());
        }
      }
    };
    this.__deletionAdapterOfAthlete = new EntityDeletionOrUpdateAdapter<Athlete>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `athletes` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Athlete entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfAthlete = new EntityDeletionOrUpdateAdapter<Athlete>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `athletes` SET `id` = ?,`username` = ?,`full_name` = ?,`age` = ?,`city` = ?,`sport` = ?,`years_experience` = ?,`bio` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Athlete entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getUsername() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getUsername());
        }
        if (entity.getFullName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getFullName());
        }
        statement.bindLong(4, entity.getAge());
        if (entity.getCity() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCity());
        }
        if (entity.getSport() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getSport());
        }
        statement.bindLong(7, entity.getYearsExperience());
        if (entity.getBio() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getBio());
        }
        statement.bindLong(9, entity.getId());
      }
    };
  }

  @Override
  public Object insertAthlete(final Athlete athlete, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAthlete.insert(athlete);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAthlete(final Athlete athlete, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfAthlete.handle(athlete);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateAthlete(final Athlete athlete, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfAthlete.handle(athlete);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<Athlete>> getAllAthletes() {
    final String _sql = "SELECT * FROM athletes";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"athletes"}, false, new Callable<List<Athlete>>() {
      @Override
      @Nullable
      public List<Athlete> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "full_name");
          final int _cursorIndexOfAge = CursorUtil.getColumnIndexOrThrow(_cursor, "age");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfSport = CursorUtil.getColumnIndexOrThrow(_cursor, "sport");
          final int _cursorIndexOfYearsExperience = CursorUtil.getColumnIndexOrThrow(_cursor, "years_experience");
          final int _cursorIndexOfBio = CursorUtil.getColumnIndexOrThrow(_cursor, "bio");
          final List<Athlete> _result = new ArrayList<Athlete>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Athlete _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpUsername;
            if (_cursor.isNull(_cursorIndexOfUsername)) {
              _tmpUsername = null;
            } else {
              _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
            }
            final String _tmpFullName;
            if (_cursor.isNull(_cursorIndexOfFullName)) {
              _tmpFullName = null;
            } else {
              _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
            }
            final int _tmpAge;
            _tmpAge = _cursor.getInt(_cursorIndexOfAge);
            final String _tmpCity;
            if (_cursor.isNull(_cursorIndexOfCity)) {
              _tmpCity = null;
            } else {
              _tmpCity = _cursor.getString(_cursorIndexOfCity);
            }
            final String _tmpSport;
            if (_cursor.isNull(_cursorIndexOfSport)) {
              _tmpSport = null;
            } else {
              _tmpSport = _cursor.getString(_cursorIndexOfSport);
            }
            final int _tmpYearsExperience;
            _tmpYearsExperience = _cursor.getInt(_cursorIndexOfYearsExperience);
            final String _tmpBio;
            if (_cursor.isNull(_cursorIndexOfBio)) {
              _tmpBio = null;
            } else {
              _tmpBio = _cursor.getString(_cursorIndexOfBio);
            }
            _item = new Athlete(_tmpId,_tmpUsername,_tmpFullName,_tmpAge,_tmpCity,_tmpSport,_tmpYearsExperience,_tmpBio);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getAthleteByUsername(final String username,
      final Continuation<? super Athlete> $completion) {
    final String _sql = "SELECT * FROM athletes WHERE username = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (username == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, username);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Athlete>() {
      @Override
      @Nullable
      public Athlete call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "full_name");
          final int _cursorIndexOfAge = CursorUtil.getColumnIndexOrThrow(_cursor, "age");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfSport = CursorUtil.getColumnIndexOrThrow(_cursor, "sport");
          final int _cursorIndexOfYearsExperience = CursorUtil.getColumnIndexOrThrow(_cursor, "years_experience");
          final int _cursorIndexOfBio = CursorUtil.getColumnIndexOrThrow(_cursor, "bio");
          final Athlete _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpUsername;
            if (_cursor.isNull(_cursorIndexOfUsername)) {
              _tmpUsername = null;
            } else {
              _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
            }
            final String _tmpFullName;
            if (_cursor.isNull(_cursorIndexOfFullName)) {
              _tmpFullName = null;
            } else {
              _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
            }
            final int _tmpAge;
            _tmpAge = _cursor.getInt(_cursorIndexOfAge);
            final String _tmpCity;
            if (_cursor.isNull(_cursorIndexOfCity)) {
              _tmpCity = null;
            } else {
              _tmpCity = _cursor.getString(_cursorIndexOfCity);
            }
            final String _tmpSport;
            if (_cursor.isNull(_cursorIndexOfSport)) {
              _tmpSport = null;
            } else {
              _tmpSport = _cursor.getString(_cursorIndexOfSport);
            }
            final int _tmpYearsExperience;
            _tmpYearsExperience = _cursor.getInt(_cursorIndexOfYearsExperience);
            final String _tmpBio;
            if (_cursor.isNull(_cursorIndexOfBio)) {
              _tmpBio = null;
            } else {
              _tmpBio = _cursor.getString(_cursorIndexOfBio);
            }
            _result = new Athlete(_tmpId,_tmpUsername,_tmpFullName,_tmpAge,_tmpCity,_tmpSport,_tmpYearsExperience,_tmpBio);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<Athlete>> getAthletesBySport(final String sport) {
    final String _sql = "SELECT * FROM athletes WHERE sport = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (sport == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, sport);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"athletes"}, false, new Callable<List<Athlete>>() {
      @Override
      @Nullable
      public List<Athlete> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUsername = CursorUtil.getColumnIndexOrThrow(_cursor, "username");
          final int _cursorIndexOfFullName = CursorUtil.getColumnIndexOrThrow(_cursor, "full_name");
          final int _cursorIndexOfAge = CursorUtil.getColumnIndexOrThrow(_cursor, "age");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfSport = CursorUtil.getColumnIndexOrThrow(_cursor, "sport");
          final int _cursorIndexOfYearsExperience = CursorUtil.getColumnIndexOrThrow(_cursor, "years_experience");
          final int _cursorIndexOfBio = CursorUtil.getColumnIndexOrThrow(_cursor, "bio");
          final List<Athlete> _result = new ArrayList<Athlete>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Athlete _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpUsername;
            if (_cursor.isNull(_cursorIndexOfUsername)) {
              _tmpUsername = null;
            } else {
              _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
            }
            final String _tmpFullName;
            if (_cursor.isNull(_cursorIndexOfFullName)) {
              _tmpFullName = null;
            } else {
              _tmpFullName = _cursor.getString(_cursorIndexOfFullName);
            }
            final int _tmpAge;
            _tmpAge = _cursor.getInt(_cursorIndexOfAge);
            final String _tmpCity;
            if (_cursor.isNull(_cursorIndexOfCity)) {
              _tmpCity = null;
            } else {
              _tmpCity = _cursor.getString(_cursorIndexOfCity);
            }
            final String _tmpSport;
            if (_cursor.isNull(_cursorIndexOfSport)) {
              _tmpSport = null;
            } else {
              _tmpSport = _cursor.getString(_cursorIndexOfSport);
            }
            final int _tmpYearsExperience;
            _tmpYearsExperience = _cursor.getInt(_cursorIndexOfYearsExperience);
            final String _tmpBio;
            if (_cursor.isNull(_cursorIndexOfBio)) {
              _tmpBio = null;
            } else {
              _tmpBio = _cursor.getString(_cursorIndexOfBio);
            }
            _item = new Athlete(_tmpId,_tmpUsername,_tmpFullName,_tmpAge,_tmpCity,_tmpSport,_tmpYearsExperience,_tmpBio);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object countAthletesBySport(final String sport,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM athletes WHERE sport = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (sport == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, sport);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
