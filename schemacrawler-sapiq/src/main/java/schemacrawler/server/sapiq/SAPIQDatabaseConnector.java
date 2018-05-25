/*
========================================================================
SchemaCrawler
http://www.schemacrawler.com
Copyright (c) 2000-2017, Sualeh Fatehi <sualeh@hotmail.com>.
All rights reserved.
------------------------------------------------------------------------

SchemaCrawler is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

SchemaCrawler and the accompanying materials are made available under
the terms of the Eclipse Public License v1.0, GNU General Public License
v3 or GNU Lesser General Public License v3.

You may elect to redistribute this code under any of these licenses.

The Eclipse Public License is available at:
http://www.eclipse.org/legal/epl-v10.html

The GNU General Public License v3 and the GNU Lesser General Public
License v3 are available at:
http://www.gnu.org/licenses/

========================================================================
*/
package schemacrawler.server.sapiq;


import java.io.IOException;
import java.sql.Connection;

import schemacrawler.schemacrawler.DatabaseSpecificOverrideOptionsBuilder;
import schemacrawler.tools.databaseconnector.DatabaseConnector;
import schemacrawler.tools.databaseconnector.DatabaseServerType;
import schemacrawler.tools.iosource.ClasspathInputResource;

public final class SAPIQDatabaseConnector
  extends DatabaseConnector
{

  public SAPIQDatabaseConnector()
    throws IOException
  {
    super(new DatabaseServerType("sapiq", "SAP IQ"),
          new ClasspathInputResource("/help/Connections.sapiq.txt"),
          new ClasspathInputResource("/schemacrawler-sapiq.config.properties"),
          (informationSchemaViewsBuilder,
           connection) -> informationSchemaViewsBuilder
             .fromResourceFolder("/sapiq.information_schema"),
          url -> true); // do not specify a value here as it
                        // conflicts with SAP ASE
  }

  @Override
  public DatabaseSpecificOverrideOptionsBuilder getDatabaseSpecificOverrideOptionsBuilder(final Connection connection)
  {
    final DatabaseSpecificOverrideOptionsBuilder databaseSpecificOverrideOptionsBuilder = super.getDatabaseSpecificOverrideOptionsBuilder(connection);
    databaseSpecificOverrideOptionsBuilder.doesNotSupportCatalogs();
    return databaseSpecificOverrideOptionsBuilder;
  }

}
