/*
 * #%L
 * servo-core
 * %%
 * Copyright (C) 2011 - 2012 Netflix
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.netflix.servo.util;

import com.netflix.servo.tag.TagList;

/**
 * Keeps track of tags that should be applied to counters incremented in the
 * current thread. Can be used to customize the context for code executed in
 * a particular thread. For example, on a server with a thread per request the
 * context can be set so metrics will be tagged accordingly.
 */
public final class TaggingContext {

    private static final ThreadLocal<TagList> CONTEXT =
        new ThreadLocal<TagList>();

    private TaggingContext() {
    }

    /** Set the tags to be associated with the current thread. */
    public static void setTags(TagList tags) {
        CONTEXT.set(tags);
    }

    /** Get the tags associated with the current thread. */
    public static TagList getTags() {
        return CONTEXT.get();
    }

    /** Remove the tags associated with the current thread. */
    public static void reset() {
        CONTEXT.remove();
    }
}