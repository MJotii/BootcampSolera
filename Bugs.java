// EJERCICIO INICIAL
public static final ByteSequence prefixEndOf(ByteSequence prefix) {
  byte[] endKey = prefix.getBytes().clone();
  for (int i = endKey.length - 1; i >= 0; i--) {
    if (endKey[i] < 0xff) {                                           
      endKey[i] = (byte) (endKey[i] + 1);
      return ByteSequence.from(Arrays.copyOf(endKey, i + 1));
    }
  }
  return ByteSequence.from(NO_PREFIX_END);
}

// EJERCICIO CORREGIDO
public static final ByteSequence prefixEndOf(ByteSequence prefix) {
  byte[] endKey = prefix.getBytes().clone();
  for (int i = endKey.length - 1; i >= 0; i--) {
    if (endKey[i] > 0xff) {           // In the endKey[i] < Oxff condition, the variable byte is compared with 255(Oxff), the maximal number of byte is 127, which means that it'll never executed the next line.                                
      endKey[i] = (byte) (endKey[i] + 1);
      return ByteSequence.from(Arrays.copyOf(endKey, i + 1));
    }
  }
  return ByteSequence.from(NO_PREFIX_END);
}

//===================================================================================================================================================================
//===================================================================================================================================================================

// EJERCICIO INICIAL
private static byte char64(char x) {
  if ((int)x < 0 || (int)x > index_64.length)
    return -1;
  return index_64[(int)x];
}

// EJERCICIO CORREGIDO
private static byte char64(char x) {
  if ((int)x < 0 || (int)x >= index_64.length) // First Bug: The (int)x<0 condition will be always false, because char type is unsigned in Java. Second Bug: We need to change the '>' operator to '>='.
    return -1;
  return index_64[(int)x];
}

//===================================================================================================================================================================
//===================================================================================================================================================================

// EJERCICIO INICIAL
@Override
public void putToCache(PutRecordsRequest putRecordsRequest)
{
  .... 
  if (dataTmpFile == null || !dataTmpFile.exists())
  {
    try
    {
      dataTmpFile.createNewFile();  // <=
    }
    catch (IOException e)
    {
      LOGGER.error("Failed to create cache tmp file, return.", e);
      return;
    }
  }
  ....
}

// EJERCICIO CORREGIDO
@Override
public void putToCache(PutRecordsRequest putRecordsRequest)
{
  .... 
  if (dataTmpFile != null && !dataTmpFile.exists()) // We need to write '!=' instead of '==', we also need to change de '||' operator to '&&'.
  {
    try
    {
      dataTmpFile.createNewFile();  // <=
    }
    catch (IOException e)
    {
      LOGGER.error("Failed to create cache tmp file, return.", e);
      return;
    }
  }
  ....
}

//===================================================================================================================================================================
//===================================================================================================================================================================

// EJERCICIO INICIAL
private void shiftRightDestructive(int wordShifts,
                                   int bitShiftsInWord,
                                   boolean roundUp) 
{
  if (wordShifts == 0 && bitShiftsInWord == 0) {
    return;
  }
  assert (wordShifts >= 0);
  assert (bitShiftsInWord >= 0);
  assert (bitShiftsInWord < 32);
  if (wordShifts >= 4) {
    zeroClear();
    return;
  }
  final int shiftRestore = 32 - bitShiftsInWord;
  // check this because "123 << 32" will be 123.
  final boolean noRestore = bitShiftsInWord == 0;
  final int roundCarryNoRestoreMask = 1 << 31;
  final int roundCarryMask = (1 << (bitShiftsInWord - 1));  
  ....
}

// EJERCICIO CORREGIDO
private void shiftRightDestructive(int wordShifts,
                                   int bitShiftsInWord,
                                   boolean roundUp) 
{
  if (wordShifts == 0 && bitShiftsInWord == 0) {
    return;
  }
  assert (wordShifts >= 0);
  assert (bitShiftsInWord >= 0);
  assert (bitShiftsInWord < 32);
  if (wordShifts >= 4) {
    zeroClear();
    return;
  }
  final int shiftRestore = 32 - bitShiftsInWord;
  // check this because "123 << 32" will be 123.
  final boolean noRestore = bitShiftsInWord == 0;
  final int roundCarryNoRestoreMask = 1 << 31;
  final int roundCarryMask = (1 << (bitShiftsInWord - 1));  // We don't expect roundCarryMask to be a negative number.
  ....
}

//===================================================================================================================================================================
//===================================================================================================================================================================

// EJERCICIO INICIAL
public void logSargResult(int stripeIx, boolean[] rgsToRead)
{
  ....
  for (int i = 0, valOffset = 0; i < elements; ++i, valOffset += 64) {
    long val = 0;
    for (int j = 0; j < 64; ++j) {
      int ix = valOffset + j;
      if (rgsToRead.length == ix) break;
      if (!rgsToRead[ix]) continue;
      val = val | (1 << j);                
    }
    ....
  }
  ....
}

// EJERCICIO CORREGIDO
public void logSargResult(int stripeIx, boolean[] rgsToRead)
{
  ....
  for (int i = 0, valOffset = 0; i < elements; ++i, valOffset += 64) {
    long val = 0;
    for (int j = 0; j < 64; ++j) {
      int ix = valOffset + j;
      if (rgsToRead.length == ix) break;
      if (!rgsToRead[ix]) continue;
      val = val | ((long)1 << j);     //the value 1 should be a long type.              
    }
    ....
  }
  ....
}

//===================================================================================================================================================================
//===================================================================================================================================================================

// EJERCICIO INICIAL
@Override
public boolean equals (Object o)
{
  ....
    CheckpointStatistics that = (CheckpointStatistics) o;
  return id == that.id &&
    savepoint == that.savepoint &&
    triggerTimestamp == that.triggerTimestamp &&
    latestAckTimestamp == that.latesAckTimestamp &&
    stateSize == that.stateSize &&
    duration == that.duration &&
    alingmentBuffered == that.alingmentBuffered && 
    processedData == processedData &&
    persistedData == that.persistedData &&
    numSubtasks == that.numSubtasks &&
    numAckSubtasks == that.numAckSubtasks &&
    status == that.status &&
    Objects.equals(checkpointType, that.checkpointType) &&
    Objects.equals(
    checkpointStatisticsPerTask,
    that.checkpointStatisticsPerTask);
}

// EJERCICIO CORREGIDO
@Override
public boolean equals (Object o)
{
  ....
    CheckpointStatistics that = (CheckpointStatistics) o;
  return id == that.id &&
    savepoint == that.savepoint &&
    triggerTimestamp == that.triggerTimestamp &&
    latestAckTimestamp == that.latesAckTimestamp &&
    stateSize == that.stateSize &&
    duration == that.duration &&
    alingmentBuffered == that.alingmentBuffered && 
    processedData == that.processedData && // That was missing.
    persistedData == that.persistedData &&
    numSubtasks == that.numSubtasks &&
    numAckSubtasks == that.numAckSubtasks &&
    status == that.status &&
    Objects.equals(checkpointType, that.checkpointType) &&
    Objects.equals(
    checkpointStatisticsPerTask,
    that.checkpointStatisticsPerTask);
}
