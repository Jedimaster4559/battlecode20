// automatically generated by the FlatBuffers compiler, do not modify

package battlecode.schema;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
/**
 * A profile contains all events and is labeled with a name.
 */
public final class ProfilerProfile extends Table {
  public static ProfilerProfile getRootAsProfilerProfile(ByteBuffer _bb) { return getRootAsProfilerProfile(_bb, new ProfilerProfile()); }
  public static ProfilerProfile getRootAsProfilerProfile(ByteBuffer _bb, ProfilerProfile obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public ProfilerProfile __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  /**
   * The display-friendly name of the profile.
   */
  public String name() { int o = __offset(4); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer nameAsByteBuffer() { return __vector_as_bytebuffer(4, 1); }
  /**
   * The events that occurred in the profile.
   */
  public ProfilerEvent events(int j) { return events(new ProfilerEvent(), j); }
  public ProfilerEvent events(ProfilerEvent obj, int j) { int o = __offset(6); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int eventsLength() { int o = __offset(6); return o != 0 ? __vector_len(o) : 0; }

  public static int createProfilerProfile(FlatBufferBuilder builder,
      int nameOffset,
      int eventsOffset) {
    builder.startObject(2);
    ProfilerProfile.addEvents(builder, eventsOffset);
    ProfilerProfile.addName(builder, nameOffset);
    return ProfilerProfile.endProfilerProfile(builder);
  }

  public static void startProfilerProfile(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addName(FlatBufferBuilder builder, int nameOffset) { builder.addOffset(0, nameOffset, 0); }
  public static void addEvents(FlatBufferBuilder builder, int eventsOffset) { builder.addOffset(1, eventsOffset, 0); }
  public static int createEventsVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startEventsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endProfilerProfile(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
}

