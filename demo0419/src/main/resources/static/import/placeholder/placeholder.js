/*
 * jQuery placeholder, fix for IE6,7,8,9, ljz 20180503
 */
var JPlaceHolder = {
	//检测
	_check: function() {
		return 'placeholder' in document.createElement('input');
	},
	//初始化
	init: function() {
		if(!this._check()) {
			this.fix();
		}
	},
	//修复
	fix: function() {
		$(':input[placeholder]').each(function(index, element) {
			var self = $(this),
				txt = self.attr('placeholder');
			self.wrap($('<div></div>').css({
				position: 'relative',
				zoom: '1',
				border: 'none',
				background: 'none',
				padding: 'none',
				margin: 'none',
				zIndex : '1'
			}));
			var pos = self.position(),
				h = self.outerHeight(true),
				paddingleft = self.css('padding-left'),
				paddingtop = self.css('padding-top');
			var holder = $('<span></span>').text(txt).css({
				position: 'absolute',
				left: pos.left,
				top: pos.top,
				height: h,
				lienHeight: h,
				paddingLeft: paddingleft,
				paddingTop: paddingtop,
				color: '#aaa',
				zIndex : '2'
			}).appendTo(self.parent());
			self.focusin(function(e) {
				holder.hide();
			}).focusout(function(e) {
				if(!self.val()) {
					holder.show();
				}
			});
			holder.click(function(e) {
				holder.hide();
				self.focus();
			});
			if(self.val()) {
				holder.hide();
			}
		});
	}
};
//执行
$(function() {
	JPlaceHolder.init();
});