package com.self.blog.email.data;

import java.io.File;

public record EmailAttachedFile(String fileName, File file) {
}
