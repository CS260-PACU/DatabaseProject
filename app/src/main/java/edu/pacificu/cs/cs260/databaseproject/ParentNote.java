package edu.pacificu.cs.cs260.databaseproject;

public class ParentNote
{
  private int mParentValue;
  private String mParentText;
  private byte[] mParentBytes;

  public int getParentValue ()
  {
    return mParentValue;
  }

  public void setParentValue (int mParentValue)
  {
    this.mParentValue = mParentValue;
  }

  public String getParentText ()
  {
    return mParentText;
  }

  public void setParentText (String mParentText)
  {
    this.mParentText = mParentText;
  }

  public byte[] getParentBytes ()
  {
    return mParentBytes;
  }

  public void setParentBytes (byte[] mParentBytes)
  {
    this.mParentBytes = mParentBytes;
  }
}
