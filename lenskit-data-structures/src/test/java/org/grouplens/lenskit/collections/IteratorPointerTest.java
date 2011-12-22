/*
 * LensKit, an open source recommender systems toolkit.
 * Copyright 2010-2011 Regents of the University of Minnesota
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */
package org.grouplens.lenskit.collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

public class IteratorPointerTest {

    @Test
    public void testEmpty() {
        Pointer<?> ptr = new IteratorPointer<Object>(Iterators.emptyIterator());
        assertTrue(ptr.isAtEnd());
        assertFalse(ptr.advance());
    }
    
    @Test(expected=NoSuchElementException.class)
    public void testEmptyGet() {
        Pointer<?> ptr = new IteratorPointer<Object>(Iterators.emptyIterator());
        ptr.get();
    }
    
    @Test
    public void testBasic() {
        List<String> strings = Lists.newArrayList("foo", "bar");
        Pointer<String> ptr = new IteratorPointer<String>(strings.iterator());
        assertFalse(ptr.isAtEnd());
        assertThat(ptr.get(), equalTo("foo"));
        assertTrue(ptr.advance());
        assertThat(ptr.get(), equalTo("bar"));
        assertFalse(ptr.isAtEnd());
        assertFalse(ptr.advance());
        assertTrue(ptr.isAtEnd());
    }

}