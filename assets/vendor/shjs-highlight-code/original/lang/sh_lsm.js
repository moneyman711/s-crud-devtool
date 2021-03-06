if (! this.sh_languages) {
  this.sh_languages = {};
}
sh_languages['lsm'] = [
  [
    [
      /\b(?:Begin[\d]*|End)\b/g,
      'sh_keyword',
      -1
    ],
    [
      /^[A-Za-z0-9_-]+:/g,
      'sh_type',
      -1
    ],
    [
      /(?:<?)[A-Za-z0-9_\.\/\-_~]+@[A-Za-z0-9_\.\/\-_~]+(?:>?)|(?:<?)[A-Za-z0-9_]+:\/\/[A-Za-z0-9_\.\/\-_~]+(?:>?)/g,
      'sh_url',
      -1
    ]
  ]
];
