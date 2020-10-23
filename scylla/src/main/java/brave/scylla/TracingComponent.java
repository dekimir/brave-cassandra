/*
 * Copyright 2017-2020 The OpenZipkin Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package brave.scylla;

import brave.Tracer;

abstract class TracingComponent {

  abstract Tracer tracer();

  static final class Current extends TracingComponent {
    @Override Tracer tracer() {
      return brave.Tracing.currentTracer();
    }
  }

  static final class Explicit extends TracingComponent {
    final Tracer tracer;

    Explicit(brave.Tracing tracing) {
      if (tracing == null) throw new NullPointerException("tracing == null");
      this.tracer = tracing.tracer();
    }

    @Override Tracer tracer() {
      return tracer;
    }
  }
}