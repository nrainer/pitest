/*
 * Copyright 2011 Henry Coles
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package org.pitest.mutationtest;

import java.util.Optional;
import org.pitest.mutationtest.engine.MutationDetails;

public final class MutationResult {

  private final MutationDetails        details;
  private final MutationStatusTestPair status;

  public MutationResult(final MutationDetails md,
      final MutationStatusTestPair status) {
    this.details = md;
    this.status = status;
  }

  public MutationDetails getDetails() {
    return this.details;
  }

  public Optional<String> getAssertionKillingTest() {
    return this.status.getAssertionKillingTests();
  }
  
  public Optional<String> getKillingTest() {
    // not to be used, only to avoid compilation failure in unused parts of descartes 
    if (getAssertionKillingTest().isPresent()) {
      return getAssertionKillingTest();
    }
    return getExceptionKillingTest();
  }
  
  public Optional<String> getExceptionKillingTest() {
    return this.status.getExceptionKillingTests();
  }

  public Optional<String> getSucceedingTest() {
    return this.status.getSucceedingTest();
  }

  public DetectionStatus getStatus() {
    return this.status.getStatus();
  }

  public int getNumberOfTestsRun() {
    return this.status.getNumberOfTestsRun();
  }

  public MutationStatusTestPair getStatusTestPair() {
    return this.status;
  }
  
  public double getDurationInMs() {
    return this.status.getDurationInMs();
  }

  public String getStatusDescription() {
    return getStatus().name();
  }

  public String getAssertionKillingTestDescription() {
    return getAssertionKillingTest().orElse("none");
  }

  public String getExceptionKillingTestDescription() {
    return getExceptionKillingTest().orElse("none");
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result)
        + ((this.details == null) ? 0 : this.details.hashCode());
    result = (prime * result)
        + ((this.status == null) ? 0 : this.status.hashCode());
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final MutationResult other = (MutationResult) obj;
    if (this.details == null) {
      if (other.details != null) {
        return false;
      }
    } else if (!this.details.equals(other.details)) {
      return false;
    }
    if (this.status == null) {
      if (other.status != null) {
        return false;
      }
    } else if (!this.status.equals(other.status)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "MutationResult [details=" + this.details + ", status="
        + this.status + "]";
  }

}