/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package allbinary.logic.communication.sql;

import abcs.business.init.db.DbConnectionInfo;

public class AbSqlTablePresentation extends AbSqlBasic
{
   
   public AbSqlTablePresentation(DbConnectionInfo databaseConnectionInfoInterface)
   {
      super(databaseConnectionInfoInterface);
   }

   /*
   public String getTable()
   {
      String sqlStatement = "SELECT * FROM " + tableName;
      
      try
      {
         this.executeQuery(sqlStatement);
         return this.getResultTable();
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
         {
            LogUtil.put("Get Table Failed\nSQL Statement: " + sqlStatement,this,"getTable",e);
         }
         return "Get Table Failed";
      }
   }
   */
   
   /*
   public String getTableWhere(String key,String value)
   {
      String sqlStatement = "SELECT * FROM " + tableName;
      sqlStatement += " WHERE ";
      sqlStatement +=  key;
      sqlStatement += " = \"";
      sqlStatement += value + "\"";
      
      try
      {
         this.executeQuery(sqlStatement);
         return this.getResultTable();
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
         {
            LogUtil.put("Get Table Failed\nSQL Statement: " + sqlStatement,this,"getTableWhere(key,value)",e);
         }
         return "getTableWhere(key,value)";
      }
   }
   
   public String getTableWhere(HashMap whereKeyValuePairs)
   {
      String sqlStatement = "SELECT * FROM " + tableName;
      
      sqlStatement += " WHERE ";
      
      Set set = whereKeyValuePairs.keySet();
      Iterator whereIter = set.iterator();
      
      while(whereIter.hasNext())
      {
         String key = (String) whereIter.next();
         String value = (String) whereKeyValuePairs.get(key);
         
         sqlStatement +=  key;
         sqlStatement += " = \"";
         sqlStatement += value + "\"";
         if(whereIter.hasNext()) sqlStatement += " AND ";
      }
      
      try
      {
         this.executeQuery(sqlStatement);
         return this.getResultTable();
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
         {
            LogUtil.put("Get Table Failed\nSQL Statement: " + sqlStatement,this,"getTableWhere()",e);
         }
         return "getTableWhere()";
      }
   }
   
   public String getTableWhereBetween(HashMap whereKeyValuePairs,
   String betweenColumn, String smallest, String largest)
   {
      String sqlStatement = "SELECT * FROM " + tableName;
      
      sqlStatement += " WHERE ";
      
      Set set = whereKeyValuePairs.keySet();
      Iterator whereIter = set.iterator();
      
      while(whereIter.hasNext())
      {
         String key = (String) whereIter.next();
         String value = (String) whereKeyValuePairs.get(key);
         
         sqlStatement +=  key;
         sqlStatement += " = \"";
         sqlStatement += value + "\"";
         sqlStatement += " AND ";
      }
      
      sqlStatement +=  betweenColumn;
      sqlStatement += " > \"";
      sqlStatement += smallest + "\"";
      sqlStatement += " AND ";
      sqlStatement +=  betweenColumn;
      sqlStatement += " < \"";
      sqlStatement += largest + "\"";
      
      try
      {
         this.executeQuery(sqlStatement);
         return this.getResultTable();
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
         {
            LogUtil.put("Get Table Failed\nSQL Statement: " + sqlStatement,this,"getTableWhereBetween()",e);
         }
         return "getTableWhereBetween() Failed";
      }
   }

    */
   
   /*
   public String getInputWhere(String key,String value)
   {
      String sqlStatement = "SELECT * FROM " + tableName;
      sqlStatement += " WHERE ";
      sqlStatement +=  key;
      sqlStatement += " = \"";
      sqlStatement += value + "\"";
      
      try
      {         
         ResultSet rset = this.executeSQLStatement(sqlStatement);
         
         ResultSetMetaData rsmd = rset.getMetaData();         
         StringBuffer stringBuff = new StringBuffer();
         
         int colNum = rsmd.getColumnCount();
         
         rset.next();
         for (int i=1; i <= colNum; i++)
         {
            String columnName = rsmd.getColumnName(i);
            String columnValue = rset.getString(i);
            if(columnValue==null || columnValue.compareTo("null)==0) columnValue="";
            stringBuff.append(new HtmlTextInput(columnName + ": ",columnName,columnValue,"<br>\n").toString());
         }                  
         
         String form = stringBuff.toString();
         return form;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLLOGGINGERROR))
         {
            LogUtil.put("Get Table Failed\nSQL Statement: " + sqlStatement,this,"getInputWhere",e);
         }
         return "Get Html Inputs Failed";
      }
   }
*/

}
